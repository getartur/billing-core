package com.getartur.billingcore.shared.domain.entities.timetracking;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TimeTracking {

    private final Long id;
    private final Long projectId;
    private final String description;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final Integer durationInMinutes;
    private final boolean billed;
    private final LocalDateTime created;

}
