package com.t.t.k.ims.kafka.consumer;

import com.t.t.k.ims.common.enums.OrderStatus;
import com.t.t.k.ims.common.utils.ObjectMapper;
import com.t.t.k.ims.kafka.BaseConsumer;
import com.t.t.k.ims.model.orders.Item;
import com.t.t.k.ims.model.orders.Order;
import com.t.t.k.ims.model.report.Sales;
import com.t.t.k.ims.repository.kafka.ConsumerFailureRepository;
import com.t.t.k.ims.service.reports.ReportService;
import lombok.Getter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


/**
 * This consumer consumes the Order Topic to for generate sales report data if the order is completed.
 *
 * <p>
 * It will check all the items in the order to generate hourly and daily sales amount, quantity and ranking of products
 *
 * @author ttkien
 */
@Service
public class SaleReportConsumer extends BaseConsumer {

    @Getter
    private final ReportService reportService;

    /**
     * The constructor to create an instance
     *
     * @param repository     the repository to save the failure messages
     * @param eventPublisher the publisher to produce failure messages
     * @param reportService  the dependent service
     */
    public SaleReportConsumer(ConsumerFailureRepository repository, ApplicationEventMulticaster eventPublisher, ReportService reportService) {
        super(repository, eventPublisher);
        this.reportService = reportService;
    }

    @KafkaListener(topics = "#{'${app.config.kafka.topics.order}'}",
            groupId = "#{'${app.config.kafka.consumer-groups.sales-report}'}",
            clientIdPrefix = "#{'${app.config.kafka.consumer-groups.sales-report}'}"
    )
    @Override
    public void listen(ConsumerRecord<?, ?> record, @Payload String message, Acknowledgment ack) throws Exception {
        process(record, message, ack);
    }

    /**
     * check if the order is completed and generate sales report data
     *
     * @param s the message
     * @throws Exception if error
     */
    @Override
    @Transactional
    protected void process(String s) throws Exception {

        Order o = ObjectMapper.instance().readValue(s, Order.class);

        if (!OrderStatus.COMPLETED.equals(o.getStatus())) return;


        for (Item i : o.getItems().values()) {
            String id = String.format("%s-%s-%s", i.getProductId(), o.getUpdatedAt().toLocalDate().toString(), o.getUpdatedAt().getHour());
            Sales sales = reportService.findSaleById(id)
                    .orElse(new Sales(id, i.getProductId(), i.getProductName(), o.getUpdatedAt().toLocalDate().toString(), o.getUpdatedAt().getHour(), BigDecimal.ZERO, 0, 0));

            sales.increaseAmount(i.getAmount());
            sales.increaseQuantity(i.getQuantity());

            reportService.logSaleRecord(sales);
        }

    }
}
