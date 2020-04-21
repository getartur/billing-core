package com.getartur.billingcore.shared.domain.entities.invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository {

    Optional<Invoice> findById(Long id);

    List<InvoiceRow> findInvoiceRowsById(Long id);

}
