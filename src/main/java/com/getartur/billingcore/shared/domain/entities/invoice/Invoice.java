package com.getartur.billingcore.shared.domain.entities.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Invoice {
    private final Long id;
    private final Long customerId;
    private final String number;
    private final LocalDate issued;
    private final String delivery;
    private final String intro;
    private final String outro;
    private final LocalDateTime created;
}
