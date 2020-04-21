package com.getartur.billingcore.features.timereport;

import com.getartur.billingcore.shared.config.CompanyProperties;
import com.getartur.billingcore.shared.domain.entities.timetracking.TimeTracking;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.leftPad;

public class TimeReportDataSource implements JRDataSource {

    private final CompanyProperties companyProperties;
    private final List<TimeTracking> rows;
    private int index = -2;

    private LocalDate start;
    private LocalDate end;
    private String projectName;

    public TimeReportDataSource(String projectName, Month month, int year, List<TimeTracking> timeTrackings, CompanyProperties companyProperties) {
        this.companyProperties = companyProperties;
        this.projectName = projectName;
        start = LocalDate.of(year, month, 1);
        end = start.plusMonths(1).minusDays(1);
        rows = timeTrackings;
    }

    @Override
    public boolean next() {
        return ++index < rows.size();
    }

    @Override
    public Object getFieldValue(JRField field) throws JRException {
        if(field.getName().equals("reportEmployee")) {
            return companyProperties.getOwner();
        }
        if(field.getName().equals("reportDateRange")) {
            return start.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                    " - " + end.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
        if(field.getName().equals("date")) {
            return rows.get(index).getStart().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
        if(field.getName().equals("start")) {
            return rows.get(index).getStart().format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        if(field.getName().equals("end")) {
            return rows.get(index).getEnd().format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        if(field.getName().equals("description")) {
            return rows.get(index).getDescription();
        }
        if(field.getName().equals("duration")) {
            long duration = ChronoUnit.MINUTES.between(rows.get(index).getStart(), rows.get(index).getEnd());
            /*if(!rows.get(index).getDurationInMinutes().equals(duration)) {
                throw new JRException("Safety check - TimeTracking field durationInMinutes doesn't equal calculated value");
            }*/
            return leftPad(duration / 60 + "", 2, "0") + ":" + leftPad(duration % 60 + "", 2, "0");
        }
        if(field.getName().equals("sumDuration")) {
            long duration = 0;
            for(TimeTracking time : rows) {
                duration += ChronoUnit.MINUTES.between(time.getStart(), time.getEnd());
            }
            return leftPad(duration / 60 + "", 2, "0") + ":" + leftPad(duration % 60 + "", 2, "0");
        }
        if(field.getName().equals("projectName")) {
            return projectName;
        }
        if(field.getName().equals("companyName")) {
            return companyProperties.getName();
        }
        if(field.getName().equals("companyFooter")) {
            return companyProperties.getOwner()
                    + ", " + companyProperties.getAddress().getLine1()
                    + (companyProperties.getAddress().getLine2() != null && companyProperties.getAddress().getLine2().length() > 0 ? ", " + companyProperties.getAddress().getLine2() : "")
                    + ", " + companyProperties.getAddress().getZip() + " " + companyProperties.getAddress().getCity();
                    //+ ", " + companyProperties.getAddress().getCountry();
        }
        return null;
    }

}
