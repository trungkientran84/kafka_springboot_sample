package com.t.t.k.ims.service.refunds;

import com.t.t.k.ims.model.orders.Payment;
import com.t.t.k.ims.model.refunds.Refund;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This interface defines all business methods available on Refund
 *
 * @author ttkien
 */
public interface RefundService {


    /**
     * Generate a new refund record
     *
     * @param oId the original order id
     * @param p the original payment
     * @return created refund
     * @throws Exception if there is any error
     */
    Refund generateRefund(String oId, Payment p) throws Exception;


    /**
     * Get all refunds.
     *
     * @param pageable The object contains pageable information
     * @return the pageable list of refundsØ
     */
    Page<Refund> findAllRefunds(Pageable pageable) throws Exception;


}
