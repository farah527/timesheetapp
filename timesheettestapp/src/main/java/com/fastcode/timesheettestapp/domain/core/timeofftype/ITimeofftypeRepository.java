package com.fastcode.timesheettestapp.domain.core.timeofftype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.time.*;
@Repository("timeofftypeRepository")
public interface ITimeofftypeRepository extends JpaRepository<TimeofftypeEntity, Long>,QuerydslPredicateExecutor<TimeofftypeEntity> {

}

