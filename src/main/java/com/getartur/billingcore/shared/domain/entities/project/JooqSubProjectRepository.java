package com.getartur.billingcore.shared.domain.entities.project;

import com.getartur.billingcore.shared.domain.jooq.tables.records.SubProjectRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.getartur.billingcore.shared.domain.jooq.Tables.*;

@Repository
@Transactional
@RequiredArgsConstructor
public class JooqSubProjectRepository implements SubProjectRepository {

    private final DSLContext dsl;

    public Optional<SubProject> findById(Long id) {
        Optional<SubProjectRecord> record = dsl.selectFrom(SUB_PROJECT).where(SUB_PROJECT.ID.eq(id)).fetchOptional();
        return record.map(this::mapToDto);
    }

    public List<SubProject> findByProjectId(Long projectId) {
        return dsl.selectFrom(SUB_PROJECT).where(SUB_PROJECT.PROJECT_ID.eq(projectId)).fetch().map(this::mapToDto);
    }

    private SubProject mapToDto(SubProjectRecord record) {
        return new SubProject(
                record.getId(),
                record.getProjectId(),
                record.getName(),
                record.getPassFactor()
        );
    }

}
