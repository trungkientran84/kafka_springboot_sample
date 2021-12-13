package com.t.t.k.ims.controller.cashiers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.t.t.k.ims.controller.cashiers.dto.CashierDTO;
import com.t.t.k.ims.model.cashiers.Cashier;
import com.t.t.k.ims.repository.cashiers.CashierRepository;
import com.t.t.k.ims.service.cashiers.CashierService;
import com.t.t.k.ims.service.cashiers.CashierServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CashierController.class})
@ExtendWith(SpringExtension.class)
class CashierControllerTest {
    @Autowired
    private CashierController cashierController;

    @MockBean
    private CashierServiceImpl cashierServiceImpl;

    @Test
    void testConstructor() {
        // Arrange
        CashierServiceImpl cashierServiceImpl = new CashierServiceImpl(mock(CashierRepository.class));

        // Act and Assert
        CashierService cashierService = (new CashierController(cashierServiceImpl)).cashierService;
        assertTrue(cashierService instanceof CashierServiceImpl);
        assertSame(cashierService, cashierServiceImpl);
    }

    @Test
    void testAddCashier() throws Exception {
        // Arrange
        Cashier cashier = new Cashier();
        cashier.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setId("42");
        cashier.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setName("Name");
        cashier.setStoreId("42");
        CashierRepository cashierRepository = mock(CashierRepository.class);
        when(cashierRepository.save((Cashier) any())).thenReturn(cashier);
        CashierController cashierController = new CashierController(new CashierServiceImpl(cashierRepository));

        CashierDTO cashierDTO = new CashierDTO();
        cashierDTO.setName("Name");
        cashierDTO.setStoreId("42");

        // Act
        ResponseEntity<Cashier> actualAddCashierResult = cashierController.addCashier(cashierDTO);

        // Assert
        assertEquals("<201 CREATED Created,Cashier(id=42, name=Name, storeId=42),[]>", actualAddCashierResult.toString());
        assertTrue(actualAddCashierResult.getHeaders().isEmpty());
        assertTrue(actualAddCashierResult.hasBody());
        assertEquals(HttpStatus.CREATED, actualAddCashierResult.getStatusCode());
        verify(cashierRepository).save((Cashier) any());
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
        CashierRepository cashierRepository = mock(CashierRepository.class);
        when(cashierRepository.save((Cashier) any())).thenReturn(cashier1);
        when(cashierRepository.findById((String) any())).thenReturn(ofResult);
        CashierController cashierController = new CashierController(new CashierServiceImpl(cashierRepository));

        CashierDTO cashierDTO = new CashierDTO();
        cashierDTO.setName("Name");
        cashierDTO.setStoreId("42");

        // Act
        ResponseEntity<Cashier> actualUpdateCashierResult = cashierController.updateCashier("42", cashierDTO);

        // Assert
        assertEquals(cashier, actualUpdateCashierResult.getBody());
        assertEquals("<200 OK OK,Cashier(id=42, name=Name, storeId=42),[]>", actualUpdateCashierResult.toString());
        assertTrue(actualUpdateCashierResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualUpdateCashierResult.getStatusCode());
        verify(cashierRepository).findById((String) any());
        verify(cashierRepository).save((Cashier) any());
    }

    @Test
    void testDeleteCashier() throws Exception {
        // Arrange
        doNothing().when(this.cashierServiceImpl).deleteCashier((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/cashiers/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.cashierController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteCashier2() throws Exception {
        // Arrange
        doNothing().when(this.cashierServiceImpl).deleteCashier((String) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/cashiers/{id}", "42");
        deleteResult.contentType("Not all who wander are lost");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.cashierController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetAllCashiers() throws Exception {
        // Arrange
        CashierRepository cashierRepository = mock(CashierRepository.class);
        when(cashierRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Cashier>(new ArrayList<Cashier>()));

        // Act
        ResponseEntity<Page<Cashier>> actualAllCashiers = (new CashierController(new CashierServiceImpl(cashierRepository)))
                .getAllCashiers(null);

        // Assert
        assertEquals("<200 OK OK,Page 1 of 1 containing UNKNOWN instances,[]>", actualAllCashiers.toString());
        assertTrue(actualAllCashiers.getBody().toList().isEmpty());
        assertTrue(actualAllCashiers.hasBody());
        assertEquals(HttpStatus.OK, actualAllCashiers.getStatusCode());
        assertTrue(actualAllCashiers.getHeaders().isEmpty());
        verify(cashierRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testFindCashierById() throws Exception {
        // Arrange
        Cashier cashier = new Cashier();
        cashier.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setId("42");
        cashier.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setName("Name");
        cashier.setStoreId("42");
        when(this.cashierServiceImpl.findById((String) any())).thenReturn(cashier);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cashiers/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.cashierController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"name\":\"Name\",\"storeId\":\"42\"}"));
    }
}

