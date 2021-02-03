package com.fastcode.timesheettestapp.domain.core.timesheet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.time.*;
@Repository("timesheetRepository")
public interface ITimesheetRepository extends JpaRepository<TimesheetEntity, Long>,QuerydslPredicateExecutor<TimesheetEntity> {

}

