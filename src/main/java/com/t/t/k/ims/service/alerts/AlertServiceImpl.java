package com.t.t.k.ims.service.alerts;

import com.t.t.k.ims.model.alerts.Alert;
import com.t.t.k.ims.repository.alerts.AlertRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * This class implement all business methods available on Alert
 *
 * @author ttkien
 */
@Service
public class AlertServiceImpl implements AlertService {

    AlertRepository alertRepository;

    /**
     * The constructor to create instance of this class
     *
     * @param alertRepository the dependent repository
     */
    public AlertServiceImpl(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    /**
     * Add a new alert
     *
     * @param o the object contains request data
     * @return created merchant store
     */
    @Override
    public Alert addAlert(Alert o) {

        return alertRepository.save(o);

    }

    /**
     * Get all alerts.
     *
     * @param pageable The object contains pageable information
     * @return the pageable list of alerts
     */
    @Override
    public Page<Alert> findAllAlerts(Pageable pageable) {
        return alertRepository.findAll(pageable);
    }


}
