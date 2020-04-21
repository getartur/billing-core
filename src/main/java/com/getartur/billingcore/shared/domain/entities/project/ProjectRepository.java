package com.getartur.billingcore.shared.domain.entities.project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {

    Optional<Project> findById(Long id);

    List<Project> findByInvoiceId(Long invoiceId);

}
