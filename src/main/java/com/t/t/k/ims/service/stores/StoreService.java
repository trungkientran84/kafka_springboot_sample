package com.t.t.k.ims.service.stores;

import com.t.t.k.ims.controller.stores.dto.StoreDTO;
import com.t.t.k.ims.model.stores.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This interface defines all business methods available on Store
 *
 * @author ttkien
 */
public interface StoreService {


    /**
     * Add a new store
     *
     * @param dto the object contains request data
     * @return created store
     * @throws Exception if there is any error
     */
    Store addStore(StoreDTO dto) throws Exception;

    /**
     * Update a store by id
     *
     * @param id  the store id to update
     * @param dto the object contains request data
     * @return updated store model
     * @throws Exception if there is any error
     */
    Store updateStore(String id, StoreDTO dto) throws Exception;


    /**
     * Delete a store by id
     *
     * @param id the store id to delete
     * @throws Exception if there is any error
     */
    void deleteStore(String id) throws Exception;

    /**
     * Find a store by id.
     *
     * @param id the id of target store
     * @return the store data model
     * @throws Exception if there is any error
     */
    Store findById(String id) throws Exception;


    /**
     * Get all stores.
     *
     * @param pageable The object contains pageable information
     * @return the pageable list of stores
     */
    Page<Store> findAllStores(Pageable pageable) throws Exception;


}
