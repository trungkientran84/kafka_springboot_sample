package com.t.t.k.ims.controller.kafka;

import com.t.t.k.ims.kafka.KafkaService;
import com.t.t.k.ims.kafka.ConsumerFailure;
import com.t.t.k.ims.kafka.ProducerFailure;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller handles endpoints all end points related to kafka
 *
 * @author ttkien
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    KafkaService kafkaService;

    public KafkaController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    /**
     * Get all consumer failure messages in the system.
     *
     * @param pageable a Pageable object contains parameters for paging
     * @return ResponseEntity with status OK and contains a pageable list consumer failure messages.
     * @throws Exception for any error occurs while retrieving data
     */
    @Operation(
            summary = "Get all consumer failure messages in the system.",
            description = "It returns all consumer failure messages in the system with the paging information.\n\n" +
                    "@param pageable a Pageable object contains parameters for paging such as size, page\n\n" +
                    "@return ResponseEntity with status OK and contains a pageable list of consumer failure messages.\n\n")
    @GetMapping("/consumer-failure-messages")
    public ResponseEntity<Page<ConsumerFailure>> getAllConsumerFailureMessages(Pageable pageable) throws Exception {
        return new ResponseEntity<>(kafkaService.getAllConsumerFailureMessages(pageable), HttpStatus.OK);
    }

    /**
     * Get all producer failure messages in the system.
     *
     * @param pageable a Pageable object contains parameters for paging
     * @return ResponseEntity with status OK and contains a pageable list producer failure messages.
     * @throws Exception for any error occurs while retrieving data
     */
    @Operation(
            summary = "Get all producer failure messages in the system.",
            description = "It returns all producer failure messages in the system with the paging information.\n\n" +
                    "@param pageable a Pageable object contains parameters for paging such as size, page\n\n" +
                    "@return ResponseEntity with status OK and contains a pageable list of producer failure messages.\n\n")
    @GetMapping("/producer-failure-messages")
    public ResponseEntity<Page<ProducerFailure>> getAllProducerFailureMessages(Pageable pageable) throws Exception {
        return new ResponseEntity<>(kafkaService.getAllProducerFailureMessages(pageable), HttpStatus.OK);
    }
}
