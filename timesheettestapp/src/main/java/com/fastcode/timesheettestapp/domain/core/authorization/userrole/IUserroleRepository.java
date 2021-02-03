package com.fastcode.timesheettestapp.domain.core.authorization.userrole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.time.*;
@Repository("userroleRepository")
public interface IUserroleRepository extends JpaRepository<UserroleEntity, UserroleId>,QuerydslPredicateExecutor<UserroleEntity> {

    List<UserroleEntity> findByUserId(Long userId);

    List<UserroleEntity> findByRoleId(Long roleId);
}

