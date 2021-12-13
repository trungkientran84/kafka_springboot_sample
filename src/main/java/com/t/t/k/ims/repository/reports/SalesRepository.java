package com.t.t.k.ims.repository.reports;

import com.t.t.k.ims.model.report.Sales;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * This interface contains the database query methods for the sale report.
 *
 * @author ttkien
 */

public interface SalesRepository extends MongoRepository<Sales, String> {

}
