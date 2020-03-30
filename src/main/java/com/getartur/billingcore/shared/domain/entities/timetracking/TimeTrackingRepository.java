package com.getartur.billingcore.shared.domain.entities.timetracking;

import java.util.List;

public interface TimeTrackingRepository {

    List<TimeTracking> findBillableHoursByProject(Long projectId);

}
