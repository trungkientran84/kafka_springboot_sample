package com.t.t.k.ims.controller.stores;

import com.t.t.k.ims.controller.stores.dto.StoreDTO;
import com.t.t.k.ims.model.stores.Store;
import com.t.t.k.ims.service.stores.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This controller handles endpoints all end points related to store
 *
 * @author ttkien
 */
@RestController
@RequestMapping("/stores")
public class StoreController {

    StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    /**
     * Create a Store.
     *
     * @param dto contains request data for create new store.
     * @return ResponseEntity  which contains created Store.
     * @throws Exception InvalidParameterException.
     */
    @Operation(
            summary = "Create a Store",
            description = "It creates and returns a new Store.\n\n" +
                    "@param: StoreDTO object in request body contains the data for create a new store.\n\n" +
                    "@return: the created Store\n\n")
    @PostMapping()
    public ResponseEntity<Store> addStore(@Valid @RequestBody StoreDTO dto) throws Exception {
        return new ResponseEntity<>(storeService.addStore(dto), HttpStatus.CREATED);
    }

    /**
     * Update a Store.
     *
     * @param dto contains request data for updating new store.
     * @return ResponseEntity  which contains updated Store.
     * @throws Exception InvalidParameterException.
     */
    @Operation(
            summary = "Update data of a Store",
            description = "It update a store by id and returns the updated store.\n\n" +
                    "@param: StoreDTO object in request body contains the data for updating store.\n\n" +
                    "@return: the updated Store\n\n")
    @PutMapping("/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable String id, @Valid @RequestBody StoreDTO dto) throws Exception {
        return new ResponseEntity<>(storeService.updateStore(id, dto), HttpStatus.OK);
    }

    /**
     * Delete a Store by its id.
     *
     * @param id the id of store.
     * @return ResponseEntity with OK status
     * @throws Exception if the there is an error of deletion
     */
    @Operation(
            summary = "Delete a Store by its id.",
            description = "It deletes a Store by given id\n\n" +
                    "@return ResponseEntity with OK status\n\n")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable String id) throws Exception {
        storeService.deleteStore(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * find a store by its id.
     *
     * @param id the id of store.
     * @return ResponseEntity object with found store.
     * @throws Exception ObjectNotFoundException
     */
    @Operation(
            summary = "Find a store by its id.",
            description = "It returns a store found by the given id.\n\n" +
                    "@param id the id of store." +
                    "@return ResponseEntity object with found store\n\n")
    @GetMapping("/{id}")
    public ResponseEntity<Store> findStoreById(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(storeService.findById(id), HttpStatus.OK);
    }

    /**
     * Get all stores in the system.
     *
     * @param pageable a Pageable object contains parameters for paging
     * @return ResponseEntity with status OK and contains a pageable list of stores.
     * @throws Exception for any error occurs while retrieving data
     */
    @Operation(
            summary = "Get all stores in the system",
            description = "It returns all stores in the system with the paging information.\n\n" +
                    "@param pageable a Pageable object contains parameters for paging such as size, page\n\n" +
                    "@return ResponseEntity with status OK and contains a pageable list of stores.\n\n")
    @GetMapping()
    public ResponseEntity<Page<Store>> getAllStores(Pageable pageable) throws Exception {
        return new ResponseEntity<>(storeService.findAllStores(pageable), HttpStatus.OK);
    }
}
