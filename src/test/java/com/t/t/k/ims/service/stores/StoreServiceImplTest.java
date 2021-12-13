package com.t.t.k.ims.service.stores;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.t.t.k.ims.controller.stores.dto.StoreDTO;
import com.t.t.k.ims.model.stores.Store;
import com.t.t.k.ims.repository.stores.StoreRepository;
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

@ContextConfiguration(classes = {StoreServiceImpl.class})
@ExtendWith(SpringExtension.class)
class StoreServiceImplTest {
    @MockBean
    private StoreRepository storeRepository;

    @Autowired
    private StoreServiceImpl storeServiceImpl;

    @Test
    void testAddStore() {
        // Arrange
        Store store = new Store();
        store.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setId("42");
        store.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setName("Name");
        when(this.storeRepository.save((Store) any())).thenReturn(store);

        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setName("Name");

        // Act and Assert
        assertSame(store, this.storeServiceImpl.addStore(storeDTO));
        verify(this.storeRepository).save((Store) any());
    }

    @Test
    void testUpdateStore() throws Exception {
        // Arrange
        Store store = new Store();
        store.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setId("42");
        store.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setName("Name");
        Optional<Store> ofResult = Optional.<Store>of(store);

        Store store1 = new Store();
        store1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store1.setId("42");
        store1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store1.setName("Name");
        when(this.storeRepository.save((Store) any())).thenReturn(store1);
        when(this.storeRepository.findById((String) any())).thenReturn(ofResult);

        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setName("Name");

        // Act and Assert
        assertSame(store1, this.storeServiceImpl.updateStore("42", storeDTO));
        verify(this.storeRepository).findById((String) any());
        verify(this.storeRepository).save((Store) any());
    }

    @Test
    void testUpdateStore2() throws Exception {
        // Arrange
        Store store = new Store();
        store.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setId("42");
        store.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setName("Name");
        when(this.storeRepository.save((Store) any())).thenReturn(store);
        when(this.storeRepository.findById((String) any())).thenReturn(Optional.<Store>empty());

        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setName("Name");

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> this.storeServiceImpl.updateStore("42", storeDTO));
        verify(this.storeRepository).findById((String) any());
    }

    @Test
    void testDeleteStore() {
        // Arrange
        doNothing().when(this.storeRepository).deleteById((String) any());

        // Act
        this.storeServiceImpl.deleteStore("42");

        // Assert
        verify(this.storeRepository).deleteById((String) any());
    }

    @Test
    void testFindById() throws Exception {
        // Arrange
        Store store = new Store();
        store.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setId("42");
        store.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setName("Name");
        Optional<Store> ofResult = Optional.<Store>of(store);
        when(this.storeRepository.findById((String) any())).thenReturn(ofResult);

        // Act and Assert
        assertSame(store, this.storeServiceImpl.findById("42"));
        verify(this.storeRepository).findById((String) any());
    }

    @Test
    void testFindById2() throws Exception {
        // Arrange
        when(this.storeRepository.findById((String) any())).thenReturn(Optional.<Store>empty());

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> this.storeServiceImpl.findById("42"));
        verify(this.storeRepository).findById((String) any());
    }

    @Test
    void testFindAllStores() {
        // Arrange
        PageImpl<Store> pageImpl = new PageImpl<Store>(new ArrayList<Store>());
        when(this.storeRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<Store> actualFindAllStoresResult = this.storeServiceImpl.findAllStores(null);

        // Assert
        assertSame(pageImpl, actualFindAllStoresResult);
        assertTrue(actualFindAllStoresResult.toList().isEmpty());
        verify(this.storeRepository).findAll((org.springframework.data.domain.Pageable) any());
    }
}

