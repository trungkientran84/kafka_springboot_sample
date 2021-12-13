package com.t.t.k.ims.controller.reports;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.t.t.k.ims.model.report.ReportData;
import com.t.t.k.ims.service.reports.ReportService;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {SaleReportController.class})
@ExtendWith(SpringExtension.class)
class SaleReportControllerTest {
    @MockBean
    private ReportService reportService;

    @Autowired
    private SaleReportController saleReportController;

    @Test
    void testGetSaleReportData() throws Exception {
        // Arrange
        when(this.reportService.getSaleReportData((LocalDate) any(), (Integer) any(), (String) any()))
                .thenReturn(new ArrayList<ReportData>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/reports/sales/data");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("date", String.valueOf(LocalDate.ofEpochDay(1L)));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.saleReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

