package com.t.t.k.ims.controller.reports;

import com.t.t.k.ims.model.report.ReportData;
import com.t.t.k.ims.service.reports.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * This controller handles all end points related to report
 *
 * @author ttkien
 */
@RestController
@RequestMapping("/reports/sales")
public class SaleReportController {

    @Getter
    private final ReportService reportService;

    public SaleReportController(ReportService reportService) {
        this.reportService = reportService;
    }

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
     * @return Ok response with all the product sales data
     */
    @Operation(
            summary = "Get sale report data of products",
            description = "It can provide the report data on daily or hourly basic all products or a specific product. The <b>>date</b parameter\n" +
                    "must be provided as the report will return data of that date. The <b>hour</b> parameter is optional. if it is\n" +
                    "provided, the api will return data of provided hour on provided date. The <b>pid</b> parameter is optional.\n" +
                    "If it is provided, the api will return data for that product only otherwise the data of all products are returned.\n\n")
    @GetMapping("/data")
    public ResponseEntity<List<ReportData>> getSaleReportData(
            @RequestParam(value = "date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(value = "hour", required = false) Integer hour,
            @RequestParam(value = "pid", required = false) String pid
    ) throws Exception {

        return new ResponseEntity<>(reportService.getSaleReportData(date, hour, pid), HttpStatus.OK);
    }

}
