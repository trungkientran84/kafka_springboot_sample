package com.t.t.k.ims.repository.cashiers;

import com.t.t.k.ims.model.cashiers.Cashier;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This interface contains the database query methods for the cashier.
 *
 * @author ttkien
 */

public interface CashierRepository extends MongoRepository<Cashier, String> {

}
