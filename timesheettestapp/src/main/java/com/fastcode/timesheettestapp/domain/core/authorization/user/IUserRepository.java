package com.fastcode.timesheettestapp.domain.core.authorization.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.time.*;
@Repository("userRepository")
public interface IUserRepository extends JpaRepository<UserEntity, Long>,QuerydslPredicateExecutor<UserEntity> {

 	@Query("select u from UserEntity u where u.userName = ?1")
    UserEntity findByUserName(String value);  
    
    UserEntity findByEmailAddress(String emailAddress);
    
}

