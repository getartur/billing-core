package com.getartur.billingcore.shared.domain.entities.customer;

import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> findById(Long id);

}
