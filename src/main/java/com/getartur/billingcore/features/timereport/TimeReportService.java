package com.getartur.billingcore.features.timereport;

import java.time.Month;

public interface TimeReportService {

    TimeDataSource createTimeReport(Long projectId, Month month, int year);

    byte[] generatePdf(TimeDataSource dataSource);

}
