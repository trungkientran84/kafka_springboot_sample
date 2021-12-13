package com.t.t.k.ims.repository.alerts;

import com.t.t.k.ims.model.alerts.Alert;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * This interface contains the database query methods for the alert.
 *
 * @author ttkien
 */

public interface AlertRepository extends MongoRepository<Alert, String> {

}
