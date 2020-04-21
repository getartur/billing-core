package com.getartur.billingcore.features.pdf;

import com.getartur.billingcore.features.invoice.InvoiceDataSource;
import com.getartur.billingcore.features.timereport.TimeReportDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class JasperPdfService implements PdfService {

    public byte[] generatePdf(String reportName, JRDataSource dataSource) {
        ClassPathResource resource = new ClassPathResource("reports/" + reportName + ".jasper");

        /* [START] jrxml -> jasper conversion only in dev - checking jasper file for prod use */
        try {
            File inputJrxml = new ClassPathResource("reports/" + reportName + ".jrxml").getFile();
            JasperCompileManager.compileReportToFile(JRXmlLoader.load(inputJrxml), resource.getFile().getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* [END]   jrxml -> jasper conversion only in dev - checking jasper file for prod use */

        Map<String, Object> hm = new HashMap<>();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            hm.put("SubDataSource", dataSource);
            // Fill the report using the custom data source
            JasperPrint jasperPrint = JasperFillManager.fillReport(resource.getInputStream(), hm, dataSource);

            // export the PDF file
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);

            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] generateMergedPdf(JRDataSource ...dataSources) {
        List<JasperPrint> printList = new ArrayList<>();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            for (JRDataSource dataSource : dataSources) {

                String reportName = "";
                if(dataSource instanceof TimeReportDataSource) {
                    reportName = "time_report";
                } else if(dataSource instanceof InvoiceDataSource) {
                    reportName = "invoice";
                }

                ClassPathResource resource = new ClassPathResource("reports/" + reportName + ".jasper");

                /* [START] jrxml -> jasper conversion only in dev - checking jasper file for prod use */
                try {
                    File inputJrxml = new ClassPathResource("reports/" + reportName + ".jrxml").getFile();
                    JasperCompileManager.compileReportToFile(JRXmlLoader.load(inputJrxml), resource.getFile().getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /* [END]   jrxml -> jasper conversion only in dev - checking jasper file for prod use */

                Map<String, Object> hm = new HashMap<>();
                hm.put("SubDataSource", dataSource);
                // Fill the report using the custom data source
                printList.add(JasperFillManager.fillReport(resource.getInputStream(), hm, dataSource));
            }

            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(SimpleExporterInput.getInstance(printList));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            configuration.setCreatingBatchModeBookmarks(true);
            exporter.setConfiguration(configuration);

            exporter.exportReport();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
