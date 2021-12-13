package com.t.t.k.ims.kafka.consumer;

import com.t.t.k.ims.common.enums.OrderStatus;
import com.t.t.k.ims.common.utils.ObjectMapper;
import com.t.t.k.ims.kafka.BaseConsumer;
import com.t.t.k.ims.model.orders.Order;
import com.t.t.k.ims.model.orders.Payment;
import com.t.t.k.ims.repository.kafka.ConsumerFailureRepository;
import com.t.t.k.ims.service.refunds.RefundService;
import lombok.Getter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * This consumer consumes the Order Topic to for generate refund record of payments the order that is CANCELED or DELETED.
 *
 * <p>
 * It will check all payments in the order to get the paid amount and payment method then generate refund records
 *
 * @author ttkien
 */
@Service
public class PaymentRefundConsumer extends BaseConsumer {
    @Getter
    private final RefundService refundService;

    /**
     * The constructor to create an instance
     *
     * @param repository     the repository to save the failure messages
     * @param eventPublisher the publisher to produce failure messages
     * @param refundService  the dependent service
     */
    public PaymentRefundConsumer(ConsumerFailureRepository repository, ApplicationEventMulticaster eventPublisher, RefundService refundService) {
        super(repository, eventPublisher);
        this.refundService = refundService;
    }

    @KafkaListener(topics = "#{'${app.config.kafka.topics.order}'}",
            groupId = "#{'${app.config.kafka.consumer-groups.payment-refund}'}",
            clientIdPrefix = "#{'${app.config.kafka.consumer-groups.payment-refund}'}"
    )
    @Override
    public void listen(ConsumerRecord<?, ?> record, @Payload String message, Acknowledgment ack) throws Exception {
        process(record, message, ack);
    }

    /**
     * If the order is canceled or deleted, issue a refund record to each payment in the order.
     *
     * @param s the message
     * @throws Exception if there is any error
     */
    @Transactional
    @Override
    protected void process(String s) throws Exception {

        Order o = ObjectMapper.instance().readValue(s, Order.class);

        if (!OrderStatus.CANCELED.equals(o.getStatus()) && !OrderStatus.DELETED.equals(o.getStatus())) return;

        for (Payment p : o.getPayments().values()) {
            refundService.generateRefund(o.getId(), p);
        }

    }
}
