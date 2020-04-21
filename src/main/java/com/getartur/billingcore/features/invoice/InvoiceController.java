package com.getartur.billingcore.features.invoice;

import com.getartur.billingcore.features.pdf.PdfService;
import com.getartur.billingcore.features.timereport.TimeReportService;
import com.getartur.billingcore.shared.domain.entities.project.Project;
import com.getartur.billingcore.shared.domain.entities.project.ProjectRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRDataSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/invoice")
@Slf4j
@RequiredArgsConstructor
@Api(tags = "invoice")
public class InvoiceController {

    private final PdfService pdfService;
    private final InvoiceService invoiceService;
    private final TimeReportService timeReportService;
    private final ProjectRepository projectRepository;

    @GetMapping(value = "/{invoiceId}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    @ApiOperation(value = "Generates the Invoice as PDF", authorizations = { @Authorization(value="apikey") })
    public ResponseEntity<byte[]> generateInvoice(@PathVariable Long invoiceId, HttpServletResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        //headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        String filename = "Invoice.pdf";
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);

        List<Project> projects = projectRepository.findByInvoiceId(invoiceId);

        JRDataSource[] dataSources = new JRDataSource[projects.size()+1];
        dataSources[0] = invoiceService.createInvoice(invoiceId, projects.get(0).getCustomerId());
        int i = 1;
        for(Project project : projects) {
            dataSources[i] = timeReportService.createTimeReport(project.getId(), invoiceId, Month.MARCH, 2020);
            i++;
        }

        byte[] responsePdf = pdfService.generateMergedPdf(
                dataSources
        );

        return new ResponseEntity(responsePdf, headers, HttpStatus.OK);
    }

}
