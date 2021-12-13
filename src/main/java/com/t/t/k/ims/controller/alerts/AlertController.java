package com.t.t.k.ims.controller.alerts;

import com.t.t.k.ims.model.alerts.Alert;
import com.t.t.k.ims.service.alerts.AlertService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller handles endpoints all end points related to alert
 *
 * @author ttkien
 */
@RestController
@RequestMapping("/alerts")
public class AlertController {

    AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    /**
     * Get all alerts in the system.
     *
     * @param pageable a Pageable object contains parameters for paging
     * @return ResponseEntity with status OK and contains a pageable list of alerts.
     * @throws Exception for any error occurs while retrieving data
     */
    @Operation(
            summary = "Get all alerts in the system",
            description = "It returns all alerts in the system with the paging information.\n\n" +
                    "@param pageable a Pageable object contains parameters for paging such as size, page\n\n" +
                    "@return ResponseEntity with status OK and contains a pageable list of alerts.\n\n")
    @GetMapping()
    public ResponseEntity<Page<Alert>> getAllAlerts(Pageable pageable) throws Exception {
        return new ResponseEntity<>(alertService.findAllAlerts(pageable), HttpStatus.OK);
    }
}
