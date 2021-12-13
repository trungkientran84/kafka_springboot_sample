package com.t.t.k.ims.service.cashiers;

import com.t.t.k.ims.controller.cashiers.dto.CashierDTO;
import com.t.t.k.ims.model.cashiers.Cashier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This interface defines all business methods available on Cashier
 *
 * @author ttkien
 */
public interface CashierService {


    /**
     * Add a new cashier
     *
     * @param dto the object contains request data
     * @return created cashier
     * @throws Exception if there is any error
     */
    Cashier addCashier(CashierDTO dto) throws Exception;

    /**
     * Update a cashier by id
     *
     * @param id  the cashier id to update
     * @param dto the object contains request data
     * @return updated cashier model
     * @throws Exception if there is any error
     */
    Cashier updateCashier(String id, CashierDTO dto) throws Exception;


    /**
     * Delete a cashier by id
     *
     * @param id the cashier id to delete
     * @throws Exception if there is any error
     */
    void deleteCashier(String id) throws Exception;

    /**
     * Find a cashier by id.
     *
     * @param id the id of target cashier
     * @return the cashier data model
     * @throws Exception if there is any error
     */
    Cashier findById(String id) throws Exception;


    /**
     * Get all cashiers.
     *
     * @param pageable The object contains pageable information
     * @return the pageable list of cashiers
     */
    Page<Cashier> findAllCashiers(Pageable pageable) throws Exception;


}
