package com.t.t.k.ims.kafka.consumer;

import com.t.t.k.ims.common.enums.OrderStatus;
import com.t.t.k.ims.common.utils.ObjectMapper;
import com.t.t.k.ims.kafka.BaseConsumer;
import com.t.t.k.ims.model.orders.Item;
import com.t.t.k.ims.model.orders.Order;
import com.t.t.k.ims.repository.kafka.ConsumerFailureRepository;
import com.t.t.k.ims.service.products.ProductService;
import lombok.Getter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * This consumer consumes the Order Topic to revert inventory count of products in the order in case the order is CANCELED
 * or DELETED.
 * <p>
 * It will check all items in the order to get the reverted quantity then put that number back to inventory count of the
 * respectively product in the store that the order was created.
 *
 * @author ttkien
 */
@Service
public class InventoryRevertConsumer extends BaseConsumer {
    @Getter
    private final ProductService productService;

    /**
     * The constructor to create an instance
     *
     * @param repository     the repository to save the failure messages
     * @param eventPublisher the publisher to produce failure messages
     * @param productService the dependent service
     */
    public InventoryRevertConsumer(ConsumerFailureRepository repository, ApplicationEventMulticaster eventPublisher, ProductService productService) {
        super(repository, eventPublisher);
        this.productService = productService;
    }


    @KafkaListener(topics = "#{'${app.config.kafka.topics.order}'}",
            groupId = "#{'${app.config.kafka.consumer-groups.inventory-revert}'}",
            clientIdPrefix = "#{'${app.config.kafka.consumer-groups.inventory-revert}'}"
    )
    @Override
    public void listen(ConsumerRecord<?, ?> record, @Payload String message, Acknowledgment ack) throws Exception {
        process(record, message, ack);
    }

    /**
     * if the order is canceled or deleted, revert the inventory count if there is any purchased items in the order
     *
     * @param s the message
     * @throws Exception
     */
    @Override
    @Transactional
    protected void process(String s) throws Exception {

        Order o = ObjectMapper.instance().readValue(s, Order.class);

        if (!OrderStatus.CANCELED.equals(o.getStatus()) && !OrderStatus.DELETED.equals(o.getStatus())) return;

        for (Item i : o.getItems().values()) {
            productService.increaseInventory(i.getProductId(), o.getStoreId(), i.getQuantity());
        }

    }
}
