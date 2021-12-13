package com.t.t.k.ims.repository.refunds;

import com.t.t.k.ims.model.refunds.Refund;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This interface contains the database query methods for the refund.
 *
 * @author ttkien
 */

public interface RefundRepository extends MongoRepository<Refund, String> {

}
