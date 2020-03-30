package com.getartur.billingcore.shared.domain.entities.project;

import com.getartur.billingcore.shared.domain.jooq.tables.records.ProjectRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.getartur.billingcore.shared.domain.jooq.Tables.PROJECT;

@Repository
@Transactional
@RequiredArgsConstructor
public class JooqProjectRepository implements ProjectRepository {

    private final DSLContext dsl;

    public Optional<Project> findById(Long id) {
        Optional<ProjectRecord> record = dsl.selectFrom(PROJECT).where(PROJECT.ID.eq(id)).fetchOptional();
        return record.map(this::mapToDto);
    }

    private Project mapToDto(ProjectRecord record) {
        return new Project(
                record.getId(),
                record.getCustomerId(),
                record.getName(),
                record.getStarted(),
                record.getFinished(),
                record.getRate(),
                record.getRateType()
        );
    }

}
