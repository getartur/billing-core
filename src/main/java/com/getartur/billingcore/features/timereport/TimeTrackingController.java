package com.getartur.billingcore.features.timereport;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TimeTrackingController {

    private final TimeReportService timeReportService;

    @GetMapping(value = "/{date}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    @ApiOperation(value = "Generates the IAR as PDF for the given date's month", authorizations = { @Authorization(value="apikey") })
    public ResponseEntity<byte[]> generateTime(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, HttpServletResponse response) {
        TimeDataSource dataSource = timeReportService.createTimeReport(3L, date.getMonth(), date.getYear());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        //headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        String filename = "TimeReport.pdf";
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);

        return new ResponseEntity(timeReportService.generatePdf(dataSource), headers, HttpStatus.OK);
    }

}
