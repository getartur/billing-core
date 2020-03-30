package com.getartur.billingcore.shared.domain.entities.project;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Project {

    private final Long id;
    private final Long customerId;
    private final String name;
    private final LocalDate started;
    private final LocalDate finished;
    private final BigDecimal rate;
    private final String rateType;

}
