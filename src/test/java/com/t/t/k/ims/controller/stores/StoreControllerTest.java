package com.t.t.k.ims.controller.stores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.t.t.k.ims.controller.stores.dto.StoreDTO;
import com.t.t.k.ims.model.stores.Store;
import com.t.t.k.ims.repository.stores.StoreRepository;
import com.t.t.k.ims.service.stores.StoreService;
import com.t.t.k.ims.service.stores.StoreServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {StoreController.class})
@ExtendWith(SpringExtension.class)
class StoreControllerTest {
    @Autowired
    private StoreController storeController;

    @MockBean
    private StoreServiceImpl storeServiceImpl;

    @Test
    void testConstructor() {
        // Arrange
        StoreServiceImpl storeServiceImpl = new StoreServiceImpl(mock(StoreRepository.class));

        // Act and Assert
        StoreService storeService = (new StoreController(storeServiceImpl)).storeService;
        assertTrue(storeService instanceof StoreServiceImpl);
        assertSame(storeService, storeServiceImpl);
    }

    @Test
    void testAddStore() throws Exception {
        // Arrange
        Store store = new Store();
        store.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setId("42");
        store.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setName("Name");
        StoreRepository storeRepository = mock(StoreRepository.class);
        when(storeRepository.save((Store) any())).thenReturn(store);
        StoreController storeController = new StoreController(new StoreServiceImpl(storeRepository));

        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setName("Name");

        // Act
        ResponseEntity<Store> actualAddStoreResult = storeController.addStore(storeDTO);

        // Assert
        assertEquals("<201 CREATED Created,Store(id=42, name=Name),[]>", actualAddStoreResult.toString());
        assertTrue(actualAddStoreResult.getHeaders().isEmpty());
        assertTrue(actualAddStoreResult.hasBody());
        assertEquals(HttpStatus.CREATED, actualAddStoreResult.getStatusCode());
        verify(storeRepository).save((Store) any());
    }

    @Test
    void testUpdateStore() throws Exception {
        // Arrange
        Store store = new Store();
        store.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setId("42");
        store.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setName("Name");
        when(this.storeServiceImpl.updateStore((String) any(), (StoreDTO) any())).thenReturn(store);

        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(storeDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/stores/{id}", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.storeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"name\":\"Name\"}"));
    }

    @Test
    void testDeleteStore() throws Exception {
        // Arrange
        doNothing().when(this.storeServiceImpl).deleteStore((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/stores/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.storeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteStore2() throws Exception {
        // Arrange
        doNothing().when(this.storeServiceImpl).deleteStore((String) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/stores/{id}", "42");
        deleteResult.contentType("Not all who wander are lost");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.storeController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetAllStores() throws Exception {
        // Arrange
        StoreRepository storeRepository = mock(StoreRepository.class);
        when(storeRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Store>(new ArrayList<Store>()));

        // Act
        ResponseEntity<Page<Store>> actualAllStores = (new StoreController(new StoreServiceImpl(storeRepository)))
                .getAllStores(null);

        // Assert
        assertEquals("<200 OK OK,Page 1 of 1 containing UNKNOWN instances,[]>", actualAllStores.toString());
        assertTrue(actualAllStores.getBody().toList().isEmpty());
        assertTrue(actualAllStores.hasBody());
        assertEquals(HttpStatus.OK, actualAllStores.getStatusCode());
        assertTrue(actualAllStores.getHeaders().isEmpty());
        verify(storeRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testFindStoreById() throws Exception {
        // Arrange
        Store store = new Store();
        store.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setId("42");
        store.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setName("Name");
        when(this.storeServiceImpl.findById((String) any())).thenReturn(store);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stores/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.storeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"name\":\"Name\"}"));
    }
}

