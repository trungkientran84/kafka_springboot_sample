package com.t.t.k.ims.service.cashiers;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.t.t.k.ims.controller.cashiers.dto.CashierDTO;
import com.t.t.k.ims.model.cashiers.Cashier;
import com.t.t.k.ims.repository.cashiers.CashierRepository;
import com.t.t.k.ims.validation.exception.ObjectNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CashierServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CashierServiceImplTest {
    @MockBean
    private CashierRepository cashierRepository;

    @Autowired
    private CashierServiceImpl cashierServiceImpl;

    @Test
    void testAddCashier() {
        // Arrange
        Cashier cashier = new Cashier();
        cashier.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setId("42");
        cashier.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setName("Name");
        cashier.setStoreId("42");
        when(this.cashierRepository.save((Cashier) any())).thenReturn(cashier);

        CashierDTO cashierDTO = new CashierDTO();
        cashierDTO.setName("Name");
        cashierDTO.setStoreId("42");

        // Act and Assert
        assertSame(cashier, this.cashierServiceImpl.addCashier(cashierDTO));
        verify(this.cashierRepository).save((Cashier) any());
    }

    @Test
    void testUpdateCashier() throws Exception {
        // Arrange
        Cashier cashier = new Cashier();
        cashier.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setId("42");
        cashier.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setName("Name");
        cashier.setStoreId("42");
        Optional<Cashier> ofResult = Optional.<Cashier>of(cashier);

        Cashier cashier1 = new Cashier();
        cashier1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setId("42");
        cashier1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setName("Name");
        cashier1.setStoreId("42");
        when(this.cashierRepository.save((Cashier) any())).thenReturn(cashier1);
        when(this.cashierRepository.findById((String) any())).thenReturn(ofResult);

        CashierDTO cashierDTO = new CashierDTO();
        cashierDTO.setName("Name");
        cashierDTO.setStoreId("42");

        // Act and Assert
        assertSame(cashier1, this.cashierServiceImpl.updateCashier("42", cashierDTO));
        verify(this.cashierRepository).findById((String) any());
        verify(this.cashierRepository).save((Cashier) any());
    }

    @Test
    void testUpdateCashier2() throws Exception {
        // Arrange
        Cashier cashier = new Cashier();
        cashier.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setId("42");
        cashier.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setName("Name");
        cashier.setStoreId("42");
        when(this.cashierRepository.save((Cashier) any())).thenReturn(cashier);
        when(this.cashierRepository.findById((String) any())).thenReturn(Optional.<Cashier>empty());

        CashierDTO cashierDTO = new CashierDTO();
        cashierDTO.setName("Name");
        cashierDTO.setStoreId("42");

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> this.cashierServiceImpl.updateCashier("42", cashierDTO));
        verify(this.cashierRepository).findById((String) any());
    }

    @Test
    void testDeleteCashier() {
        // Arrange
        doNothing().when(this.cashierRepository).deleteById((String) any());

        // Act
        this.cashierServiceImpl.deleteCashier("42");

        // Assert
        verify(this.cashierRepository).deleteById((String) any());
    }

    @Test
    void testFindById() throws Exception {
        // Arrange
        Cashier cashier = new Cashier();
        cashier.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setId("42");
        cashier.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setName("Name");
        cashier.setStoreId("42");
        Optional<Cashier> ofResult = Optional.<Cashier>of(cashier);
        when(this.cashierRepository.findById((String) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(cashier, this.cashierServiceImpl.findById("42"));
        verify(this.cashierRepository).findById((String) any());
    }

    @Test
    void testFindById2() throws Exception {
        // Arrange
        when(this.cashierRepository.findById((String) any())).thenReturn(Optional.<Cashier>empty());

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> this.cashierServiceImpl.findById("42"));
        verify(this.cashierRepository).findById((String) any());
    }

    @Test
    void testFindAllCashiers() {
        // Arrange
        PageImpl<Cashier> pageImpl = new PageImpl<Cashier>(new ArrayList<Cashier>());
        when(this.cashierRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<Cashier> actualFindAllCashiersResult = this.cashierServiceImpl.findAllCashiers(null);

        // Assert
        assertSame(pageImpl, actualFindAllCashiersResult);
        assertTrue(actualFindAllCashiersResult.toList().isEmpty());
        verify(this.cashierRepository).findAll((org.springframework.data.domain.Pageable) any());
    }
}

