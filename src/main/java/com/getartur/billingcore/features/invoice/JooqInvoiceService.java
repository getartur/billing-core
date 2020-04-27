package com.getartur.billingcore.features.invoice;

import com.getartur.billingcore.shared.config.CompanyProperties;
import com.getartur.billingcore.shared.domain.entities.customer.Customer;
import com.getartur.billingcore.shared.domain.entities.customer.CustomerRepository;
import com.getartur.billingcore.shared.domain.entities.invoice.Invoice;
import com.getartur.billingcore.shared.domain.entities.invoice.InvoiceRepository;
import com.getartur.billingcore.shared.domain.entities.invoice.InvoiceRow;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class JooqInvoiceService implements InvoiceService {

    private final InvoiceRepository repository;
    private final CustomerRepository customerRepository;
    private final CompanyProperties companyProperties;

    public InvoiceDataSource createInvoice(Long id) {
        Invoice invoice = repository.findById(id).orElse(null);
        Customer customer = customerRepository.findById(invoice != null ? invoice.getCustomerId() : null).orElse(null);
        List<InvoiceRow> invoiceRows = repository.findInvoiceRowsById(id);

        return new InvoiceDataSource(invoice, customer, invoiceRows, companyProperties);
    }

    public Invoice findById(Long id) {
        return repository.findById(id).orElse(null);
    }

}
