package com.fastcode.timesheettestapp.domain.core.appconfiguration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.time.*;
@Repository("appConfigurationRepository")
public interface IAppConfigurationRepository extends JpaRepository<AppConfigurationEntity, Long>,QuerydslPredicateExecutor<AppConfigurationEntity> {

}

