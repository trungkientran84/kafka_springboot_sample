package com.t.t.k.ims.service.cashiers;

import com.t.t.k.ims.common.utils.ModelMapper;
import com.t.t.k.ims.controller.cashiers.dto.CashierDTO;
import com.t.t.k.ims.model.cashiers.Cashier;
import com.t.t.k.ims.repository.cashiers.CashierRepository;
import com.t.t.k.ims.validation.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implement all business methods available on cashier
 *
 * @author ttkien
 */
@Service
public class CashierServiceImpl implements CashierService {

    CashierRepository cashierRepository;

    /**
     * The constructor to create instance of this class
     *
     * @param cashierRepository the dependent repository
     */
    public CashierServiceImpl(CashierRepository cashierRepository) {
        this.cashierRepository = cashierRepository;
    }

    /**
     * Add a new cashier
     *
     * @param dto the object contains request data
     * @return created merchant store
     */
    @Override
    public Cashier addCashier(CashierDTO dto) {

        Cashier c = new Cashier();

        ModelMapper.intance().map(dto, c);

        return cashierRepository.save(c);

    }

    /**
     * Update a cashier by id
     *
     * @param id  the cashier id to update
     * @param dto the object contains request data
     * @return updated cashier model
     * @throws Exception if there is any error
     */
    @Override
    @Transactional
    public Cashier updateCashier(String id, CashierDTO dto) throws Exception {

        Cashier c = cashierRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cashier", id));

        ModelMapper.intance().map(dto, c);

        return cashierRepository.save(c);

    }

    /**
     * Delete a cashier by id
     *
     * @param id the cashier id to delete
     */
    @Override
    public void deleteCashier(String id) {
        //TODO: validate cashier being used in orders
        cashierRepository.deleteById(id);
    }

    /**
     * Find a cashier by id.
     *
     * @param id the id of target cashier
     * @return the cashier data model
     * @throws Exception if there is any error
     */
    @Override
    public Cashier findById(String id) throws Exception {
        return cashierRepository.findById(id).orElseThrow(()
                -> new ObjectNotFoundException("Cashier", id));
    }

    /**
     * Get all cashiers.
     *
     * @param pageable The object contains pageable information
     * @return the pageable list of cashiers
     */
    @Override
    public Page<Cashier> findAllCashiers(Pageable pageable) {
        return cashierRepository.findAll(pageable);
    }


}
