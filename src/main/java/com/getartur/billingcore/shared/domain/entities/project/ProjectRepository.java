package com.getartur.billingcore.shared.domain.entities.project;

import java.util.Optional;

public interface ProjectRepository {

    Optional<Project> findById(Long id);

}
