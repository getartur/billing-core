package com.getartur.billingcore.shared.domain.entities.project;

import com.getartur.billingcore.shared.domain.jooq.tables.records.ProjectRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record7;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.getartur.billingcore.shared.domain.jooq.Tables.*;

@Repository
@Transactional
@RequiredArgsConstructor
public class JooqProjectRepository implements ProjectRepository {

    private final DSLContext dsl;

    public Optional<Project> findById(Long id) {
        Optional<ProjectRecord> record = dsl.selectFrom(PROJECT).where(PROJECT.ID.eq(id)).fetchOptional();
        return record.map(this::mapToDto);
    }

    public List<Project> findByInvoiceId(Long invoiceId) {
        Result<Record7<Long, Long, String, LocalDate, LocalDate, BigDecimal, String>> result = dsl.select(
                PROJECT.ID,
                PROJECT.CUSTOMER_ID,
                PROJECT.NAME,
                PROJECT.STARTED,
                PROJECT.FINISHED,
                PROJECT.RATE,
                PROJECT.RATE_TYPE
        ).from(
                TIME_TRACKING.leftJoin(PROJECT).on(TIME_TRACKING.PROJECT_ID.eq(PROJECT.ID))
        ).where(
                TIME_TRACKING.INVOICE_ID.eq(invoiceId)
        ).groupBy(PROJECT.ID).orderBy(PROJECT.ID.asc()).fetch();

        return result.stream().map(x -> new Project(x.component1(), x.component2(), x.component3(), x.component4(), x.component5(), x.component6(), x.component7())).collect(Collectors.toList());
    }

    private Project mapToDto(ProjectRecord record) {
        return new Project(
                record.getId(),
                record.getCustomerId(),
                record.getName(),
                record.getStarted(),
                record.getFinished(),
                record.getRate(),
                record.getRateType()
        );
    }

}
