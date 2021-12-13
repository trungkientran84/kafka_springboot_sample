package com.t.t.k.ims.controller.refunds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.t.t.k.ims.model.refunds.Refund;
import com.t.t.k.ims.repository.refunds.RefundRepository;
import com.t.t.k.ims.service.refunds.RefundService;
import com.t.t.k.ims.service.refunds.RefundServiceImpl;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class RefundControllerTest {
    @Test
    void testConstructor() {
        // Arrange
        RefundServiceImpl refundServiceImpl = new RefundServiceImpl(mock(RefundRepository.class));

        // Act and Assert
        RefundService refundService = (new RefundController(refundServiceImpl)).refundService;
        assertTrue(refundService instanceof RefundServiceImpl);
        assertSame(refundService, refundServiceImpl);
    }

    @Test
    void testGetAllRefunds() throws Exception {
        // Arrange
        RefundRepository refundRepository = mock(RefundRepository.class);
        when(refundRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Refund>(new ArrayList<Refund>()));

        // Act
        ResponseEntity<Page<Refund>> actualAllRefunds = (new RefundController(new RefundServiceImpl(refundRepository)))
                .getAllRefunds(null);

        // Assert
        assertEquals("<200 OK OK,Page 1 of 1 containing UNKNOWN instances,[]>", actualAllRefunds.toString());
        assertTrue(actualAllRefunds.getBody().toList().isEmpty());
        assertTrue(actualAllRefunds.hasBody());
        assertEquals(HttpStatus.OK, actualAllRefunds.getStatusCode());
        assertTrue(actualAllRefunds.getHeaders().isEmpty());
        verify(refundRepository).findAll((org.springframework.data.domain.Pageable) any());
    }
}

