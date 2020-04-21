package com.getartur.billingcore.shared.domain.entities.customer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    private final Long id;
    private final String name;
    private final String number;
    private final String phone;
    private final String website;
    private final String vatin;
}
