package com.getartur.billingcore.features.timereport;

import java.time.Month;

public interface TimeReportService {

    TimeReportDataSource createTimeReport(Long projectId, Long invoiceId, Month month, int year);

    byte[] generatePdf(TimeReportDataSource dataSource);

}
