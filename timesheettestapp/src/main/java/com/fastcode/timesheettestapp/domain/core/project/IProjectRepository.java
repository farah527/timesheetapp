package com.fastcode.timesheettestapp.domain.core.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.time.*;
@Repository("projectRepository")
public interface IProjectRepository extends JpaRepository<ProjectEntity, Long>,QuerydslPredicateExecutor<ProjectEntity> {

}

