package com.t.t.k.ims.service.alerts;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.t.t.k.ims.common.enums.AlertType;
import com.t.t.k.ims.model.alerts.Alert;
import com.t.t.k.ims.repository.alerts.AlertRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AlertServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AlertServiceImplTest {
    @MockBean
    private AlertRepository alertRepository;

    @Autowired
    private AlertServiceImpl alertServiceImpl;

    @Test
    void testAddAlert() {
        // Arrange
        Alert alert = new Alert(AlertType.INVENTORY_THRESHOLD, "Messages");

        when(this.alertRepository.save((Alert) any())).thenReturn(alert);

        // Act and Assert
        assertSame(alert, this.alertServiceImpl.addAlert(new Alert(AlertType.INVENTORY_THRESHOLD, "Messages")));
        verify(this.alertRepository).save((Alert) any());
    }

    @Test
    void testFindAllAlerts() {
        // Arrange
        PageImpl<Alert> pageImpl = new PageImpl<Alert>(new ArrayList<Alert>());
        when(this.alertRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<Alert> actualFindAllAlertsResult = this.alertServiceImpl.findAllAlerts(null);

        // Assert
        assertSame(pageImpl, actualFindAllAlertsResult);
        assertTrue(actualFindAllAlertsResult.toList().isEmpty());
        verify(this.alertRepository).findAll((org.springframework.data.domain.Pageable) any());
    }
}

