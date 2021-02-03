package com.fastcode.timesheettestapp.application.core.users;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import com.fastcode.timesheettestapp.application.core.users.dto.*;
import com.fastcode.timesheettestapp.domain.core.users.IUsersRepository;
import com.fastcode.timesheettestapp.domain.core.users.QUsersEntity;
import com.fastcode.timesheettestapp.domain.core.users.UsersEntity;
import com.fastcode.timesheettestapp.commons.search.*;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;
import com.querydsl.core.BooleanBuilder;

import java.time.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

@Service("usersAppService")
@RequiredArgsConstructor
public class UsersAppService implements IUsersAppService {

	@Qualifier("usersRepository")
	@NonNull protected final IUsersRepository _usersRepository;

	@Qualifier("IUsersMapperImpl")
	@NonNull protected final IUsersMapper mapper;

	@NonNull protected final LoggingHelper logHelper;

    @Transactional(propagation = Propagation.REQUIRED)
	public CreateUsersOutput create(CreateUsersInput input) {

		UsersEntity users = mapper.createUsersInputToUsersEntity(input);

		UsersEntity createdUsers = _usersRepository.save(users);
		return mapper.usersEntityToCreateUsersOutput(createdUsers);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public UpdateUsersOutput update(Long usersId, UpdateUsersInput input) {

		UsersEntity users = mapper.updateUsersInputToUsersEntity(input);
		
		UsersEntity updatedUsers = _usersRepository.save(users);
		return mapper.usersEntityToUpdateUsersOutput(updatedUsers);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long usersId) {

		UsersEntity existing = _usersRepository.findById(usersId).orElse(null); 
	 	_usersRepository.delete(existing);
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public FindUsersByIdOutput findById(Long usersId) {

		UsersEntity foundUsers = _usersRepository.findById(usersId).orElse(null);
		if (foundUsers == null)  
			return null; 
 	   
 	    return mapper.usersEntityToFindUsersByIdOutput(foundUsers);
	}
	
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<FindUsersByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception  {

		Page<UsersEntity> foundUsers = _usersRepository.findAll(search(search), pageable);
		List<UsersEntity> usersList = foundUsers.getContent();
		Iterator<UsersEntity> usersIterator = usersList.iterator(); 
		List<FindUsersByIdOutput> output = new ArrayList<>();

		while (usersIterator.hasNext()) {
		UsersEntity users = usersIterator.next();
 	    output.add(mapper.usersEntityToFindUsersByIdOutput(users));
		}
		return output;
	}
	
	protected BooleanBuilder search(SearchCriteria search) throws Exception {

		QUsersEntity users= QUsersEntity.usersEntity;
		if(search != null) {
			Map<String,SearchFields> map = new HashMap<>();
			for(SearchFields fieldDetails: search.getFields())
			{
				map.put(fieldDetails.getFieldName(),fieldDetails);
			}
			List<String> keysList = new ArrayList<String>(map.keySet());
			checkProperties(keysList);
			return searchKeyValuePair(users, map,search.getJoinColumns());
		}
		return null;
	}
	
	protected void checkProperties(List<String> list) throws Exception  {
		for (int i = 0; i < list.size(); i++) {
			if(!(
				list.get(i).replace("%20","").trim().equals("emailaddress") ||
				list.get(i).replace("%20","").trim().equals("firstname") ||
				list.get(i).replace("%20","").trim().equals("id") ||
				list.get(i).replace("%20","").trim().equals("isactive") ||
				list.get(i).replace("%20","").trim().equals("isemailconfirmed") ||
				list.get(i).replace("%20","").trim().equals("joinDate") ||
				list.get(i).replace("%20","").trim().equals("lastname") ||
				list.get(i).replace("%20","").trim().equals("password") ||
				list.get(i).replace("%20","").trim().equals("username")
			)) 
			{
			 throw new Exception("Wrong URL Format: Property " + list.get(i) + " not found!" );
			}
		}
	}
	
	protected BooleanBuilder searchKeyValuePair(QUsersEntity users, Map<String,SearchFields> map,Map<String,String> joinColumns) {
		BooleanBuilder builder = new BooleanBuilder();
        
		for (Map.Entry<String, SearchFields> details : map.entrySet()) {
            if(details.getKey().replace("%20","").trim().equals("emailaddress")) {
				if(details.getValue().getOperator().equals("contains"))
					builder.and(users.emailaddress.likeIgnoreCase("%"+ details.getValue().getSearchValue() + "%"));
				else if(details.getValue().getOperator().equals("equals"))
					builder.and(users.emailaddress.eq(details.getValue().getSearchValue()));
				else if(details.getValue().getOperator().equals("notEqual"))
					builder.and(users.emailaddress.ne(details.getValue().getSearchValue()));
			}
            if(details.getKey().replace("%20","").trim().equals("firstname")) {
				if(details.getValue().getOperator().equals("contains"))
					builder.and(users.firstname.likeIgnoreCase("%"+ details.getValue().getSearchValue() + "%"));
				else if(details.getValue().getOperator().equals("equals"))
					builder.and(users.firstname.eq(details.getValue().getSearchValue()));
				else if(details.getValue().getOperator().equals("notEqual"))
					builder.and(users.firstname.ne(details.getValue().getSearchValue()));
			}
			if(details.getKey().replace("%20","").trim().equals("id")) {
				if(details.getValue().getOperator().equals("equals") && StringUtils.isNumeric(details.getValue().getSearchValue()))
					builder.and(users.id.eq(Long.valueOf(details.getValue().getSearchValue())));
				else if(details.getValue().getOperator().equals("notEqual") && StringUtils.isNumeric(details.getValue().getSearchValue()))
					builder.and(users.id.ne(Long.valueOf(details.getValue().getSearchValue())));
				else if(details.getValue().getOperator().equals("range"))
				{
				   if(StringUtils.isNumeric(details.getValue().getStartingValue()) && StringUtils.isNumeric(details.getValue().getEndingValue()))
                	   builder.and(users.id.between(Long.valueOf(details.getValue().getStartingValue()), Long.valueOf(details.getValue().getEndingValue())));
                   else if(StringUtils.isNumeric(details.getValue().getStartingValue()))
                	   builder.and(users.id.goe(Long.valueOf(details.getValue().getStartingValue())));
                   else if(StringUtils.isNumeric(details.getValue().getEndingValue()))
                	   builder.and(users.id.loe(Long.valueOf(details.getValue().getEndingValue())));
				}
			}
			if(details.getKey().replace("%20","").trim().equals("isactive")) {
				if(details.getValue().getOperator().equals("equals") && (details.getValue().getSearchValue().equalsIgnoreCase("true") || details.getValue().getSearchValue().equalsIgnoreCase("false")))
					builder.and(users.isactive.eq(Boolean.parseBoolean(details.getValue().getSearchValue())));
				else if(details.getValue().getOperator().equals("notEqual") && (details.getValue().getSearchValue().equalsIgnoreCase("true") || details.getValue().getSearchValue().equalsIgnoreCase("false")))
					builder.and(users.isactive.ne(Boolean.parseBoolean(details.getValue().getSearchValue())));
			}
			if(details.getKey().replace("%20","").trim().equals("isemailconfirmed")) {
				if(details.getValue().getOperator().equals("equals") && (details.getValue().getSearchValue().equalsIgnoreCase("true") || details.getValue().getSearchValue().equalsIgnoreCase("false")))
					builder.and(users.isemailconfirmed.eq(Boolean.parseBoolean(details.getValue().getSearchValue())));
				else if(details.getValue().getOperator().equals("notEqual") && (details.getValue().getSearchValue().equalsIgnoreCase("true") || details.getValue().getSearchValue().equalsIgnoreCase("false")))
					builder.and(users.isemailconfirmed.ne(Boolean.parseBoolean(details.getValue().getSearchValue())));
			}
			if(details.getKey().replace("%20","").trim().equals("joinDate")) {
				if(details.getValue().getOperator().equals("equals") && SearchUtils.stringToLocalDate(details.getValue().getSearchValue()) !=null)
					builder.and(users.joinDate.eq(SearchUtils.stringToLocalDate(details.getValue().getSearchValue())));
				else if(details.getValue().getOperator().equals("notEqual") && SearchUtils.stringToLocalDate(details.getValue().getSearchValue()) !=null)
					builder.and(users.joinDate.ne(SearchUtils.stringToLocalDate(details.getValue().getSearchValue())));
				else if(details.getValue().getOperator().equals("range"))
				{
				   LocalDate startLocalDate= SearchUtils.stringToLocalDate(details.getValue().getStartingValue());
				   LocalDate endLocalDate= SearchUtils.stringToLocalDate(details.getValue().getEndingValue());
				   if(startLocalDate!=null && endLocalDate!=null)	 
					   builder.and(users.joinDate.between(startLocalDate,endLocalDate));
				   else if(endLocalDate!=null)
					   builder.and(users.joinDate.loe(endLocalDate));
                   else if(startLocalDate!=null)
                	   builder.and(users.joinDate.goe(startLocalDate));  
                 }
                   
			}
            if(details.getKey().replace("%20","").trim().equals("lastname")) {
				if(details.getValue().getOperator().equals("contains"))
					builder.and(users.lastname.likeIgnoreCase("%"+ details.getValue().getSearchValue() + "%"));
				else if(details.getValue().getOperator().equals("equals"))
					builder.and(users.lastname.eq(details.getValue().getSearchValue()));
				else if(details.getValue().getOperator().equals("notEqual"))
					builder.and(users.lastname.ne(details.getValue().getSearchValue()));
			}
            if(details.getKey().replace("%20","").trim().equals("password")) {
				if(details.getValue().getOperator().equals("contains"))
					builder.and(users.password.likeIgnoreCase("%"+ details.getValue().getSearchValue() + "%"));
				else if(details.getValue().getOperator().equals("equals"))
					builder.and(users.password.eq(details.getValue().getSearchValue()));
				else if(details.getValue().getOperator().equals("notEqual"))
					builder.and(users.password.ne(details.getValue().getSearchValue()));
			}
            if(details.getKey().replace("%20","").trim().equals("username")) {
				if(details.getValue().getOperator().equals("contains"))
					builder.and(users.username.likeIgnoreCase("%"+ details.getValue().getSearchValue() + "%"));
				else if(details.getValue().getOperator().equals("equals"))
					builder.and(users.username.eq(details.getValue().getSearchValue()));
				else if(details.getValue().getOperator().equals("notEqual"))
					builder.and(users.username.ne(details.getValue().getSearchValue()));
			}
	    
		}
		
		return builder;
	}
	

	public Map<String,String> parseTimesheetsJoinColumn(String keysString) {
		
		Map<String,String> joinColumnMap = new HashMap<String,String>();
		joinColumnMap.put("userid", keysString);
		  
		return joinColumnMap;
	}
    
	public Map<String,String> parseUsertasksJoinColumn(String keysString) {
		
		Map<String,String> joinColumnMap = new HashMap<String,String>();
		joinColumnMap.put("userid", keysString);
		  
		return joinColumnMap;
	}
    
}



