package com.getartur.billingcore.shared.domain.entities.project;

import java.util.List;
import java.util.Optional;

public interface SubProjectRepository {

    Optional<SubProject> findById(Long id);

    List<SubProject> findByProjectId(Long projectId);

}
