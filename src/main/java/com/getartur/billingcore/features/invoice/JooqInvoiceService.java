package com.getartur.billingcore.features.invoice;

import com.getartur.billingcore.shared.config.CompanyProperties;
import com.getartur.billingcore.shared.domain.entities.customer.Customer;
import com.getartur.billingcore.shared.domain.entities.customer.CustomerRepository;
import com.getartur.billingcore.shared.domain.entities.invoice.Invoice;
import com.getartur.billingcore.shared.domain.entities.invoice.InvoiceRepository;
import com.getartur.billingcore.shared.domain.entities.invoice.InvoiceRow;
import com.getartur.billingcore.shared.domain.entities.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class JooqInvoiceService implements InvoiceService {

    private final InvoiceRepository repository;
    private final CustomerRepository customerRepository;
    private final ProjectRepository projectRepository;
    private final CompanyProperties companyProperties;

    public InvoiceDataSource createInvoice(Long invoiceId, Long customerId) {
        Optional<Invoice> invoice = repository.findById(invoiceId);
        Optional<Customer> customer = customerRepository.findById(customerId);
        List<InvoiceRow> invoiceRows = repository.findInvoiceRowsById(invoiceId);

        return new InvoiceDataSource(invoice.orElse(null), customer.orElse(null), invoiceRows, companyProperties);
    }

    public byte[] generatePdf(InvoiceDataSource dataSource) {
        ClassPathResource resource = new ClassPathResource("reports/invoice.jasper");

        /* [START] jrxml -> jasper conversion only in dev - checking jasper file for prod use */
        try {
            File inputJrxml = new ClassPathResource("reports/invoice.jrxml").getFile();
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

}
