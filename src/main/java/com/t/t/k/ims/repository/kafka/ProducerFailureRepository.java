package com.t.t.k.ims.repository.kafka;

import com.t.t.k.ims.kafka.ProducerFailure;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This interface contains the database query methods for the ProducerFailure.
 *
 * @author ttkien
 */

public interface ProducerFailureRepository extends MongoRepository<ProducerFailure, String> {

}
