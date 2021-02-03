package com.fastcode.timesheettestapp.application.core.users;

import org.springframework.data.domain.Pageable;
import com.fastcode.timesheettestapp.application.core.users.dto.*;
import com.fastcode.timesheettestapp.commons.search.SearchCriteria;

import java.util.*;

public interface IUsersAppService {
	
	//CRUD Operations
	
	CreateUsersOutput create(CreateUsersInput users);

    void delete(Long id);

    UpdateUsersOutput update(Long id, UpdateUsersInput input);

    FindUsersByIdOutput findById(Long id);

    List<FindUsersByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception;
    
    //Join Column Parsers

	Map<String,String> parseTimesheetsJoinColumn(String keysString);

	Map<String,String> parseUsertasksJoinColumn(String keysString);
}

