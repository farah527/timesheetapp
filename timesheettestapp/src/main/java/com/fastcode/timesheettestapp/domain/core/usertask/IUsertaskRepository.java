package com.fastcode.timesheettestapp.domain.core.usertask;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.time.*;
@Repository("usertaskRepository")
public interface IUsertaskRepository extends JpaRepository<UsertaskEntity, UsertaskId>,QuerydslPredicateExecutor<UsertaskEntity> {

}

