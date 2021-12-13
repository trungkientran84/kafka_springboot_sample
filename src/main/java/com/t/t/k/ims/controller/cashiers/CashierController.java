package com.t.t.k.ims.controller.cashiers;

import com.t.t.k.ims.controller.cashiers.dto.CashierDTO;
import com.t.t.k.ims.model.cashiers.Cashier;
import com.t.t.k.ims.service.cashiers.CashierService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This controller handles endpoints all end points related to cashier
 *
 * @author ttkien
 */
@RestController
@RequestMapping("/cashiers")
public class CashierController {

    CashierService cashierService;

    public CashierController(CashierService cashierService) {
        this.cashierService = cashierService;
    }

    /**
     * Create a Cashier.
     *
     * @param dto contains request data for create new cashier.
     * @return ResponseEntity  which contains created Cashier.
     * @throws Exception InvalidParameterException.
     */
    @Operation(
            summary = "Create a Cashier",
            description = "It creates and returns a new Cashier.\n\n" +
                    "@param: CashierDTO object in request body contains the data for create a new cashier.\n\n" +
                    "@return: the created Cashier\n\n")
    @PostMapping()
    public ResponseEntity<Cashier> addCashier(@Valid @RequestBody CashierDTO dto) throws Exception {
        return new ResponseEntity<>(cashierService.addCashier(dto), HttpStatus.CREATED);
    }

    /**
     * Update a Cashier.
     *
     * @param dto contains request data for updating new cashier.
     * @return ResponseEntity  which contains updated Cashier.
     * @throws Exception InvalidParameterException.
     */
    @Operation(
            summary = "Update data of a Cashier",
            description = "It update a cashier by id and returns the updated cashier.\n\n" +
                    "@param: CashierDTO object in request body contains the data for updating cashier.\n\n" +
                    "@return: the updated Cashier\n\n")
    @PutMapping("/{id}")
    public ResponseEntity<Cashier> updateCashier(@PathVariable String id, @Valid @RequestBody CashierDTO dto) throws Exception {
        return new ResponseEntity<>(cashierService.updateCashier(id, dto), HttpStatus.OK);
    }

    /**
     * Delete a Cashier by its id.
     *
     * @param id the id of cashier.
     * @return ResponseEntity with OK status
     * @throws Exception if the there is an error of deletion
     */
    @Operation(
            summary = "Delete a Cashier by its id.",
            description = "It deletes a Cashier by given id\n\n" +
                    "@return ResponseEntity with OK status\n\n")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCashier(@PathVariable String id) throws Exception {
        cashierService.deleteCashier(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * find a cashier by its id.
     *
     * @param id the id of cashier.
     * @return ResponseEntity object with found cashier.
     * @throws Exception ObjectNotFoundException
     */
    @Operation(
            summary = "Find a cashier by its id.",
            description = "It returns a cashier found by the given id.\n\n" +
                    "@param id the id of cashier." +
                    "@return ResponseEntity object with found cashier\n\n")
    @GetMapping("/{id}")
    public ResponseEntity<Cashier> findCashierById(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(cashierService.findById(id), HttpStatus.OK);
    }

    /**
     * Get all cashiers in the system.
     *
     * @param pageable a Pageable object contains parameters for paging
     * @return ResponseEntity with status OK and contains a pageable list of cashiers.
     * @throws Exception for any error occurs while retrieving data
     */
    @Operation(
            summary = "Get all cashiers in the system",
            description = "It returns all cashiers in the system with the paging information.\n\n" +
                    "@param pageable a Pageable object contains parameters for paging such as size, page\n\n" +
                    "@return ResponseEntity with status OK and contains a pageable list of cashiers.\n\n")
    @GetMapping()
    public ResponseEntity<Page<Cashier>> getAllCashiers(Pageable pageable) throws Exception {
        return new ResponseEntity<>(cashierService.findAllCashiers(pageable), HttpStatus.OK);
    }
}
