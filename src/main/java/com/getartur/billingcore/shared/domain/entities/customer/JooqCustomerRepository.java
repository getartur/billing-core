package com.getartur.billingcore.shared.domain.entities.customer;

import com.getartur.billingcore.shared.domain.jooq.tables.records.CustomerRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.getartur.billingcore.shared.domain.jooq.Tables.*;

@Repository
@Transactional
@RequiredArgsConstructor
public class JooqCustomerRepository implements CustomerRepository {

    private final DSLContext dsl;

    public Optional<Customer> findById(Long id) {
        Optional<CustomerRecord> record = dsl.selectFrom(CUSTOMER).where(CUSTOMER.ID.eq(id)).fetchOptional();
        return record.map(this::mapToDto);
    }

    private Customer mapToDto(CustomerRecord record) {
        return new Customer(
                record.getId(),
                record.getName(),
                record.getNumber(),
                record.getPhone(),
                record.getWebsite(),
                record.getVatin()
        );
    }

}
