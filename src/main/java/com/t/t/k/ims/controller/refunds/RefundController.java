package com.t.t.k.ims.controller.refunds;

import com.t.t.k.ims.model.refunds.Refund;
import com.t.t.k.ims.service.refunds.RefundService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller handles endpoints all end points related to refund records
 *
 * @author ttkien
 */
@RestController
@RequestMapping("/refunds")
public class RefundController {

    RefundService refundService;

    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }

    /**
     * Get all refunds in the system.
     *
     * @param pageable a Pageable object contains parameters for paging
     * @return ResponseEntity with status OK and contains a pageable list of refunds.
     * @throws Exception for any error occurs while retrieving data
     */
    @Operation(
            summary = "Get all refunds in the system",
            description = "It returns all refunds in the system with the paging information.\n\n" +
                    "@param pageable a Pageable object contains parameters for paging such as size, page\n\n" +
                    "@return ResponseEntity with status OK and contains a pageable list of refunds.\n\n")
    @GetMapping()
    public ResponseEntity<Page<Refund>> getAllRefunds(Pageable pageable) throws Exception {
        return new ResponseEntity<>(refundService.findAllRefunds(pageable), HttpStatus.OK);
    }
}
