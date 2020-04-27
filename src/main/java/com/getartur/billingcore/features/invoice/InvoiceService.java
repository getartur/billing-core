package com.getartur.billingcore.features.invoice;

import com.getartur.billingcore.shared.domain.entities.invoice.Invoice;

public interface InvoiceService {

    InvoiceDataSource createInvoice(Long id);

    Invoice findById(Long id);

}
