package com.getartur.billingcore.features.pdf;

import net.sf.jasperreports.engine.JRDataSource;

public interface PdfService {

    byte[] generatePdf(String reportName, JRDataSource dataSource);

    byte[] generateMergedPdf(JRDataSource ...dataSources);

}
