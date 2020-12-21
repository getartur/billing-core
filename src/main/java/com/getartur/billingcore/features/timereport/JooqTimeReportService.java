package com.getartur.billingcore.features.timereport;

import com.getartur.billingcore.shared.config.CompanyProperties;
import com.getartur.billingcore.shared.domain.entities.project.ProjectRepository;
import com.getartur.billingcore.shared.domain.entities.project.Project;
import com.getartur.billingcore.shared.domain.entities.project.SubProject;
import com.getartur.billingcore.shared.domain.entities.project.SubProjectRepository;
import com.getartur.billingcore.shared.domain.entities.timetracking.TimeTracking;
import com.getartur.billingcore.shared.domain.entities.timetracking.TimeTrackingRepository;
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
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class JooqTimeReportService implements TimeReportService {

    private final TimeTrackingRepository repository;
    private final ProjectRepository projectRepository;
    private final SubProjectRepository subProjectRepository;
    private final CompanyProperties companyProperties;

    public TimeReportDataSource createTimeReport(Long projectId, Long invoiceId, Month month, int year) {
        List<TimeTracking> timeTrackings = repository.findBillableHoursByProjectAndInvoice(projectId, invoiceId);
        List<SubProject> subProjects = subProjectRepository.findByProjectId(projectId);
        Project project = projectRepository.findById(projectId).orElse(null);
        String projectName = "";
        if(project != null) {
            projectName = project.getName();
        }

        return new TimeReportDataSource(projectName, month, year, subProjects, timeTrackings, companyProperties);
    }

    public byte[] generatePdf(TimeReportDataSource dataSource) {
        ClassPathResource resource = new ClassPathResource("reports/time_report.jasper");

        /* [START] jrxml -> jasper conversion only in dev - checking jasper file for prod use */
        try {
            File inputJrxml = new ClassPathResource("reports/time_report.jrxml").getFile();
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
