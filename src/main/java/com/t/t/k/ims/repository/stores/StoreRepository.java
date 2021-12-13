package com.t.t.k.ims.repository.stores;

import com.t.t.k.ims.model.stores.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This interface contains the database query methods for the stores.
 *
 * @author ttkien
 */

public interface StoreRepository extends MongoRepository<Store, String> {

}
