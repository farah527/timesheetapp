package com.fastcode.timesheettestapp.application.core.authorization.userrole;

import com.fastcode.timesheettestapp.domain.core.authorization.userrole.UserroleId;
import org.springframework.data.domain.Pageable;
import com.fastcode.timesheettestapp.application.core.authorization.userrole.dto.*;
import com.fastcode.timesheettestapp.commons.search.SearchCriteria;

import java.util.*;

public interface IUserroleAppService {
	
	//CRUD Operations
	
	CreateUserroleOutput create(CreateUserroleInput userrole);

    void delete(UserroleId userroleId);

    UpdateUserroleOutput update(UserroleId userroleId, UpdateUserroleInput input);

    FindUserroleByIdOutput findById(UserroleId userroleId);

    List<FindUserroleByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception;
	//Relationship Operations
	//Relationship Operations
    
    GetRoleOutput getRole(UserroleId userroleId);
    
    GetUserOutput getUser(UserroleId userroleId);
    
    //Join Column Parsers
    
	UserroleId parseUserroleKey(String keysString);
}

