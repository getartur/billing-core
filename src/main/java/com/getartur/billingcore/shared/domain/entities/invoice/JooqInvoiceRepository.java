package com.getartur.billingcore.shared.domain.entities.invoice;

import com.getartur.billingcore.shared.domain.jooq.tables.records.InvoiceRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.getartur.billingcore.shared.domain.jooq.Tables.*;
import static org.jooq.impl.DSL.sum;

@Repository
@Transactional
@RequiredArgsConstructor
public class JooqInvoiceRepository implements InvoiceRepository {

    private final DSLContext dsl;

    public Optional<Invoice> findById(Long id) {
        Optional<InvoiceRecord> record = dsl.selectFrom(INVOICE).where(INVOICE.ID.eq(id)).fetchOptional();
        return record.map(this::mapToDto);
    }

    public List<InvoiceRow> findInvoiceRowsById(Long invoiceId) {
        Result<Record6<Long, String, BigDecimal, BigDecimal, String, BigDecimal>> result = dsl.select(
                PROJECT.ID,
                PROJECT.NAME,
                sum(TIME_TRACKING.DURATION_IN_MINUTES).div(60),
                PROJECT.RATE,
                PROJECT.RATE_TYPE,
                sum(TIME_TRACKING.DURATION_IN_MINUTES).div(60).mul(PROJECT.RATE)
        ).from(
                TIME_TRACKING.leftJoin(PROJECT).on(TIME_TRACKING.PROJECT_ID.eq(PROJECT.ID))
        ).where(
                TIME_TRACKING.INVOICE_ID.eq(invoiceId)
        ).groupBy(PROJECT.ID).orderBy(PROJECT.ID.asc()).fetch();

        return result.stream().map(x -> new InvoiceRow(x.component1(), x.component2(), x.component3(), x.component4(), x.component5(), new BigDecimal(20.0), x.component6())).collect(Collectors.toList());
    }

    private Invoice mapToDto(InvoiceRecord record) {
        return new Invoice(
                record.getId(),
                record.getCustomerId(),
                record.getNumber(),
                record.getIssued(),
                record.getDelivery(),
                record.getIntro(),
                record.getOutro(),
                record.getCreated()
        );
    }

}
