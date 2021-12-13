package com.t.t.k.ims.service.reports;

import com.t.t.k.ims.model.report.ReportData;
import com.t.t.k.ims.model.report.Sales;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * This interface defines all business methods available on Report
 *
 * @author ttkien
 */
public interface ReportService {


    /**
     * Add a new report
     *
     * @param o the object contains request data
     * @return created report
     * @throws Exception if there is any error
     */
    Sales logSaleRecord(Sales o) throws Exception;

    /**
     * Find a sale report record by id.
     *
     * @param id the id of target sale
     * @return the optional sale object
     * @throws Exception if there is any error
     */
    Optional<Sales> findSaleById(String id) throws Exception;


    /**
     * Get sale report data of products
     * <p>
     * It can provide the report data on daily or hourly basic all products or a specific product. The <b>>date</b parameter
     * must be provided as the report will return data of that date. The <b>hour</b> parameter is optional. if it is
     * provided, the api will return data of provided hour on provided date. The <b>pid</b> parameter is optional.
     * If it is provided, the api will return data for that product only otherwise the data of all products are returned
     *
     * @param date the date to retrieve data.
     * @param hour the hour of above date ranging from 0 to 23 and is optional
     * @param pid  Filter Orders by serveOptions values. Ex- Pickup, Delivery, Dine in.
     * @param pid  The product id, it is optional
     * @return list of report data
     * @throws Exception if any error
     */
    List<ReportData> getSaleReportData(LocalDate date, Integer hour, String pid) throws Exception;


}
