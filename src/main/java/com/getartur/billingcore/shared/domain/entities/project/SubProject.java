package com.getartur.billingcore.shared.domain.entities.project;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SubProject {

    private final Long id;
    private final Long projectId;
    private final String name;
    private final BigDecimal passFactor;

}
