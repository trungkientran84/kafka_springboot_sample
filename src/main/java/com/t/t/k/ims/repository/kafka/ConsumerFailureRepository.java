package com.t.t.k.ims.repository.kafka;

import com.t.t.k.ims.kafka.ConsumerFailure;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This interface contains the database query methods for the ConsumerFailure.
 *
 * @author ttkien
 */

public interface ConsumerFailureRepository extends MongoRepository<ConsumerFailure, String> {

}
