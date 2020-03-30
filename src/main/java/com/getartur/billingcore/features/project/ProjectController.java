package com.getartur.billingcore.features.project;

import com.getartur.billingcore.shared.domain.entities.project.Project;
import com.getartur.billingcore.shared.domain.entities.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectRepository repository;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public Project generateTime(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

}
