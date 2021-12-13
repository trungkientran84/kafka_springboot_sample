package com.t.t.k.ims.service.reports;

import com.t.t.k.ims.common.utils.ModelMapper;
import com.t.t.k.ims.model.report.ReportData;
import com.t.t.k.ims.model.report.Sales;
import com.t.t.k.ims.repository.reports.SalesRepository;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/**
 * This class implement all business methods available on report
 *
 * @author ttkien
 */
@Service
public class ReportServiceImpl implements ReportService {

    private final String COLLECTION = "Sales";
    private final String PRODUCT_ID = "productId";
    private final String NAME = "name";
    private final String DATE = "date";
    private final String HOUR = "hour";
    private final String QUALITY = "quality";
    private final String AMOUNT = "amount";


    @Getter
    private final SalesRepository salesRepository;

    private final MongoTemplate mongoTemplate;

    /**
     * The constructor to create instance of this class
     *
     * @param salesRepository the dependent repository
     * @param mongoTemplate   mongo template
     */
    public ReportServiceImpl(SalesRepository salesRepository, MongoTemplate mongoTemplate) {
        this.salesRepository = salesRepository;
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Add a new report
     *
     * @param o the object contains request data
     * @return created report
     * @throws Exception if there is any error
     */
    @Override
    public Sales logSaleRecord(Sales o) throws Exception {
        return salesRepository.save(o);
    }

    /**
     * Find a sale report by id.
     *
     * @param id the id of target sale
     * @return the optional sale object
     * @throws Exception if there is any error
     */
    @Override
    public Optional<Sales> findSaleById(String id) throws Exception {
        return salesRepository.findById(id);
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
     * @return pageble of SaleReportDTO
     * @throws Exception if any error
     */

    @Override
    public List<ReportData> getSaleReportData(LocalDate date, Integer hour, String pid) throws Exception {
        if (hour != null) return findHourlySalesReportData(date, hour, pid);

        return findDailySalesReportData(date, pid);


    }

    /**
     * Find hourly sale report data based on provided information
     *
     * @param date the date to search data
     * @param hour the hour to search data
     * @param pid  the product id
     * @return List of report data
     */
    private List<ReportData> findHourlySalesReportData(LocalDate date, Integer hour, String pid) {

        Criteria criteria = new Criteria(DATE).is(date.toString()).and(HOUR).is(hour);
        List<ReportData> result = new ArrayList<>();

        if (pid != null) {
            Optional<Sales> r = salesRepository.findById(String.format("%s-%s-%s", pid, date, hour));
            if (r.isPresent()) {

                criteria.and(QUALITY).gte(r.get().getQuality());


                AggregationResults<Rank> ar = mongoTemplate.aggregate(
                        newAggregation(match(criteria), group(HOUR).count().as("rank"))
                        , COLLECTION
                        , Rank.class);

                ReportData d = new ReportData();
                ModelMapper.intance().map(r.get(), d);

                d.setRank(ar.getUniqueMappedResult().rank);

                result.add(d);

            }

        } else {
            AggregationResults<ReportData> ar = mongoTemplate.aggregate(
                    newAggregation(match(criteria), sort(Sort.Direction.DESC, QUALITY))
                    , COLLECTION
                    , ReportData.class);

            result = ar.getMappedResults();
            for (int i = 0; i < result.size(); i++) {
                result.get(i).setRank(i + 1);
            }
        }

        return result;
    }

    /**
     * Find sale report data based on provided information
     *
     * @param date the date to search data
     * @param pid  the product id
     * @return Page Object for retrieved Orders.
     */
    private List<ReportData> findDailySalesReportData(LocalDate date, String pid) {

        Aggregation aggregation = newAggregation(
                match(new Criteria(DATE).is(date.toString()))
                , group(DATE, PRODUCT_ID, NAME).sum(QUALITY).as(QUALITY).sum(AMOUNT).as(AMOUNT).push(AMOUNT).as("accumulateAmount")
                , project(DATE, PRODUCT_ID, NAME, AMOUNT, QUALITY, "accumulateAmount")
                , sort(Sort.by(Sort.Direction.DESC, QUALITY)));


        AggregationResults<ReportData> result = mongoTemplate.aggregate(aggregation, COLLECTION, ReportData.class);

        List<ReportData> l = result.getMappedResults();

        for (int i = 0; i < l.size(); i++) {
            ReportData rd = l.get(i);
            rd.setRank(i + 1);
            rd.calcAmount();
            if (rd.getProductId().equals(pid)) {
                return Arrays.asList(rd);
            }
        }

        return l;
    }

    @Data
    class Rank {
        int rank;
    }
}
