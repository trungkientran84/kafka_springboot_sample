package com.t.t.k.ims.service.stores;

import com.t.t.k.ims.common.utils.ModelMapper;
import com.t.t.k.ims.controller.stores.dto.StoreDTO;
import com.t.t.k.ims.model.stores.Store;
import com.t.t.k.ims.repository.stores.StoreRepository;
import com.t.t.k.ims.validation.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implement all business methods available on stores
 *
 * @author ttkien
 */
@Service
public class StoreServiceImpl implements StoreService {

    StoreRepository storeRepository;

    /**
     * The constructor to create instance of this class
     *
     * @param storeRepository the dependent repository
     */
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    /**
     * Add a new store
     *
     * @param dto the object contains request data
     * @return created merchant store
     */
    @Override
    public Store addStore(StoreDTO dto) {

        Store c = new Store();

        ModelMapper.intance().map(dto, c);

        return storeRepository.save(c);

    }

    /**
     * Update a store by id
     *
     * @param id  the store id to update
     * @param dto the object contains request data
     * @return updated store model
     * @throws Exception if there is any error
     */
    @Override
    @Transactional
    public Store updateStore(String id, StoreDTO dto) throws Exception {

        Store c = storeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Store", id));

        ModelMapper.intance().map(dto, c);

        return storeRepository.save(c);

    }

    /**
     * Delete a store by id
     *
     * @param id the store id to delete
     */
    @Override
    public void deleteStore(String id) {
        //TODO: validate store being used in orders and cashier
        storeRepository.deleteById(id);
    }

    /**
     * Find a store by id.
     *
     * @param id the id of target store
     * @return the store data model
     * @throws Exception if there is any error
     */
    @Override
    public Store findById(String id) throws Exception {
        return storeRepository.findById(id).orElseThrow(()
                -> new ObjectNotFoundException("Store", id));
    }

    /**
     * Get all stores.
     *
     * @param pageable The object contains pageable information
     * @return the pageable list of stores
     */
    @Override
    public Page<Store> findAllStores(Pageable pageable) {
        return storeRepository.findAll(pageable);
    }


}
