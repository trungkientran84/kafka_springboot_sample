package com.t.t.k.ims.controller.alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.t.t.k.ims.model.alerts.Alert;
import com.t.t.k.ims.repository.alerts.AlertRepository;
import com.t.t.k.ims.service.alerts.AlertService;
import com.t.t.k.ims.service.alerts.AlertServiceImpl;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class AlertControllerTest {
    @Test
    void testConstructor() {
        // Arrange
        AlertServiceImpl alertServiceImpl = new AlertServiceImpl(mock(AlertRepository.class));

        // Act and Assert
        AlertService alertService = (new AlertController(alertServiceImpl)).alertService;
        assertTrue(alertService instanceof AlertServiceImpl);
        assertSame(alertService, alertServiceImpl);
    }

    @Test
    void testGetAllAlerts() throws Exception {
        // Arrange
        AlertRepository alertRepository = mock(AlertRepository.class);
        when(alertRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Alert>(new ArrayList<Alert>()));

        // Act
        ResponseEntity<Page<Alert>> actualAllAlerts = (new AlertController(new AlertServiceImpl(alertRepository)))
                .getAllAlerts(null);

        // Assert
        assertEquals("<200 OK OK,Page 1 of 1 containing UNKNOWN instances,[]>", actualAllAlerts.toString());
        assertTrue(actualAllAlerts.getBody().toList().isEmpty());
        assertTrue(actualAllAlerts.hasBody());
        assertEquals(HttpStatus.OK, actualAllAlerts.getStatusCode());
        assertTrue(actualAllAlerts.getHeaders().isEmpty());
        verify(alertRepository).findAll((org.springframework.data.domain.Pageable) any());
    }
}

