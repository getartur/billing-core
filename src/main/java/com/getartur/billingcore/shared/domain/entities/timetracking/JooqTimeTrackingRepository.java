package com.getartur.billingcore.shared.domain.entities.timetracking;

import com.getartur.billingcore.shared.domain.jooq.tables.records.TimeTrackingRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record8;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.getartur.billingcore.shared.domain.jooq.Tables.PROJECT;
import static com.getartur.billingcore.shared.domain.jooq.Tables.TIME_TRACKING;

@Repository
@Transactional
@RequiredArgsConstructor
public class JooqTimeTrackingRepository implements TimeTrackingRepository {

    private final DSLContext dsl;

    public List<TimeTracking> findBillableHoursByProject(Long projectId) {
        return findBillableHoursByProjectAndInvoice(projectId, null);
    }

    public List<TimeTracking> findBillableHoursByProjectAndInvoice(Long projectId, Long invoiceId) {
        Result<Record8<Long, Long, Long, String, LocalDateTime, LocalDateTime, Integer, LocalDateTime>> result = dsl.select(
                TIME_TRACKING.ID,
                TIME_TRACKING.PROJECT_ID,
                TIME_TRACKING.INVOICE_ID,
                TIME_TRACKING.DESCRIPTION,
                TIME_TRACKING.START,
                TIME_TRACKING.END,
                TIME_TRACKING.DURATION_IN_MINUTES,
                TIME_TRACKING.CREATED
        ).from(
                TIME_TRACKING.leftJoin(PROJECT).on(TIME_TRACKING.PROJECT_ID.eq(PROJECT.ID))
        ).where(
                PROJECT.ID.eq(projectId).and(TIME_TRACKING.INVOICE_ID.eq(invoiceId))
        ).orderBy(TIME_TRACKING.START.asc()).fetch();

        return result.stream().map(x -> new TimeTracking(x.component1(), x.component2(), x.component3(), x.component4(), x.component5(), x.component6(), x.component7(), x.component8())).collect(Collectors.toList());
    }

    private TimeTracking mapToDto(TimeTrackingRecord record) {
        return new TimeTracking(
                record.getId(),
                record.getProjectId(),
                record.getInvoiceId(),
                record.getDescription(),
                record.getStart(),
                record.getEnd(),
                record.getDurationInMinutes(),
                record.getCreated()
        );
    }

}
