package com.t.t.k.ims.service.refunds;

import com.t.t.k.ims.common.enums.RefundStatus;
import com.t.t.k.ims.model.orders.Payment;
import com.t.t.k.ims.model.refunds.Refund;
import com.t.t.k.ims.repository.refunds.RefundRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * This class implement all business methods available on refund
 *
 * @author ttkien
 */
@Service
public class RefundServiceImpl implements RefundService {

    RefundRepository refundRepository;

    /**
     * The constructor to create instance of this class
     *
     * @param refundRepository the dependent repository
     */
    public RefundServiceImpl(RefundRepository refundRepository) {
        this.refundRepository = refundRepository;
    }

    /**
     * Generate a new refund record
     *
     * @param oId the original order id
     * @param p   the original payment
     * @return created refund
     * @throws Exception if there is any error
     */
    @Override
    public Refund generateRefund(String oId, Payment p) throws Exception {
        return refundRepository.save(new Refund(oId, p, RefundStatus.OPEN));
    }

    /**
     * Get all refunds.
     *
     * @param pageable The object contains pageable information
     * @return the pageable list of refundsØ
     */
    @Override
    public Page<Refund> findAllRefunds(Pageable pageable) throws Exception {
        return refundRepository.findAll(pageable);
    }
}
