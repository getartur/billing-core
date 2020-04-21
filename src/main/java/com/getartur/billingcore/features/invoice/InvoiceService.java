package com.getartur.billingcore.features.invoice;

public interface InvoiceService {

    InvoiceDataSource createInvoice(Long invoiceId, Long customerId);

    byte[] generatePdf(InvoiceDataSource dataSource);

}
