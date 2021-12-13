package com.t.t.k.ims.service.alerts;

import com.t.t.k.ims.model.alerts.Alert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This interface defines all business methods available on Alert
 *
 * @author ttkien
 */
public interface AlertService {


    /**
     * Add a new alert
     *
     * @param o the object contains request data
     * @return created alert
     * @throws Exception if there is any error
     */
    Alert addAlert(Alert o) throws Exception;


    /**
     * Get all alerts.
     *
     * @param pageable The object contains pageable information
     * @return the pageable list of alerts
     */
    Page<Alert> findAllAlerts(Pageable pageable) throws Exception;


}
