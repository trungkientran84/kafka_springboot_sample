package com.t.t.k.ims.kafka.consumer;

import com.t.t.k.ims.common.enums.AlertType;
import com.t.t.k.ims.common.utils.ObjectMapper;
import com.t.t.k.ims.kafka.BaseConsumer;
import com.t.t.k.ims.model.alerts.Alert;
import com.t.t.k.ims.model.products.Inventory;
import com.t.t.k.ims.model.products.Product;
import com.t.t.k.ims.repository.kafka.ConsumerFailureRepository;
import com.t.t.k.ims.service.alerts.AlertService;
import com.t.t.k.ims.service.products.ProductService;
import lombok.Getter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


/**
 * This class consumes the inventory topic to observer any change in inventory and generate a alert
 * if the amount of any product in any store falls below the predefined threshold
 *
 * @author ttkien
 */
@Service
public class InventoryAlertConsumer extends BaseConsumer {
    @Getter
    private final AlertService alertService;
    @Getter
    private final ProductService productService;

    /**
     * The constructor to initialize an instance
     *
     * @param repository     the repository to store failure messages
     * @param eventPublisher the publisher to publish failure messages
     * @param alertService   the dependent service
     * @param productService the dependent service
     */
    public InventoryAlertConsumer(
            ConsumerFailureRepository repository
            , ApplicationEventMulticaster eventPublisher
            , AlertService alertService
            , ProductService productService) {

        super(repository, eventPublisher);
        this.alertService = alertService;
        this.productService = productService;
    }

    @KafkaListener(topics = "#{'${app.config.kafka.topics.inventory}'}",
            groupId = "#{'${app.config.kafka.consumer-groups.inventory-alert}'}",
            clientIdPrefix = "#{'${app.config.kafka.consumer-groups.inventory-alert}'}"
    )
    @Override
    public void listen(ConsumerRecord<?, ?> record, @Payload String message, Acknowledgment ack) throws Exception {
        process(record, message, ack);
    }

    /**
     * Check if the inventory count is less than inventory threshold then generate a inventory threshold alert
     *
     * @param s the message
     * @throws Exception if error
     */
    @Override
    protected void process(String s) throws Exception {

        Inventory obj = ObjectMapper.instance().readValue(s, Inventory.class);

        Product p = productService.findById(obj.getProductId());

        if (obj.getCount() < p.getInventoryThreshold()) {
            alertService.addAlert(
                    new Alert(AlertType.INVENTORY_THRESHOLD
                            , String
                            .format(
                                    "The inventory count of the product [%s] in the store [%s] is below the inventory threshold. Count: %s. Threshold: %s "
                                    , obj.getProductId()
                                    , obj.getStoreId()
                                    , obj.getCount()
                                    , p.getInventoryThreshold())
                    ));
        }
    }
}
