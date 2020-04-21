package com.getartur.billingcore.shared.domain.entities.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class InvoiceRow {
    private final Long projectId;
    private final String projectName;
    private final BigDecimal count;
    private final BigDecimal rate;
    private final String rateType;
    private final BigDecimal vatRate;
    private final BigDecimal amount;
}
