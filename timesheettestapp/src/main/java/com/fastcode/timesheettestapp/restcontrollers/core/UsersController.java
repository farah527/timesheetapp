package com.fastcode.timesheettestapp.restcontrollers.core;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fastcode.timesheettestapp.commons.search.SearchCriteria;
import com.fastcode.timesheettestapp.commons.search.SearchUtils;
import com.fastcode.timesheettestapp.commons.search.OffsetBasedPageRequest;
import com.fastcode.timesheettestapp.application.core.users.IUsersAppService;
import com.fastcode.timesheettestapp.application.core.users.dto.*;
import com.fastcode.timesheettestapp.application.core.timesheet.ITimesheetAppService;
import com.fastcode.timesheettestapp.application.core.timesheet.dto.FindTimesheetByIdOutput;
import com.fastcode.timesheettestapp.application.core.usertask.IUsertaskAppService;
import com.fastcode.timesheettestapp.application.core.usertask.dto.FindUsertaskByIdOutput;
import java.util.*;
import java.time.*;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

	@Qualifier("usersAppService")
	@NonNull protected final IUsersAppService _usersAppService;

    @Qualifier("timesheetAppService")
	@NonNull  protected final ITimesheetAppService  _timesheetAppService;

    @Qualifier("usertaskAppService")
	@NonNull  protected final IUsertaskAppService  _usertaskAppService;

	@NonNull protected final LoggingHelper logHelper;

	@NonNull protected final Environment env;

    @PreAuthorize("hasAnyAuthority('USERSENTITY_CREATE')")
	@RequestMapping(method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<CreateUsersOutput> create(@RequestBody @Valid CreateUsersInput users) {
		CreateUsersOutput output=_usersAppService.create(users);
		return new ResponseEntity(output, HttpStatus.OK);
	}

	// ------------ Delete users ------------
	@PreAuthorize("hasAnyAuthority('USERSENTITY_DELETE')")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = {"application/json"})
	public void delete(@PathVariable String id) {

    	FindUsersByIdOutput output = _usersAppService.findById(Long.valueOf(id));
    	Optional.ofNullable(output).orElseThrow(() -> new EntityNotFoundException(String.format("There does not exist a users with a id=%s", id)));

    	_usersAppService.delete(Long.valueOf(id));
    }


	// ------------ Update users ------------
    @PreAuthorize("hasAnyAuthority('USERSENTITY_UPDATE')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<UpdateUsersOutput> update(@PathVariable String id, @RequestBody @Valid UpdateUsersInput users) {

	    FindUsersByIdOutput currentUsers = _usersAppService.findById(Long.valueOf(id));
		Optional.ofNullable(currentUsers).orElseThrow(() -> new EntityNotFoundException(String.format("Unable to update. Users with id=%s not found.", id)));


		users.setVersiono(currentUsers.getVersiono());
	    UpdateUsersOutput output = _usersAppService.update(Long.valueOf(id),users);
		return new ResponseEntity(output, HttpStatus.OK);
	}


    @PreAuthorize("hasAnyAuthority('USERSENTITY_READ')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<FindUsersByIdOutput> findById(@PathVariable String id) {

    	FindUsersByIdOutput output = _usersAppService.findById(Long.valueOf(id));
    	Optional.ofNullable(output).orElseThrow(() -> new EntityNotFoundException(String.format("Not found")));

		return new ResponseEntity(output, HttpStatus.OK);
	}

    @PreAuthorize("hasAnyAuthority('USERSENTITY_READ')")
	@RequestMapping(method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity find(@RequestParam(value="search", required=false) String search, @RequestParam(value = "offset", required=false) String offset, @RequestParam(value = "limit", required=false) String limit, Sort sort) throws Exception {

		if (offset == null) { offset = env.getProperty("fastCode.offset.default"); }
		if (limit == null) { limit = env.getProperty("fastCode.limit.default"); }

		if(sort == null || sort.isEmpty()) {
			sort = Sort.by(Sort.Direction.ASC, "lastname");
		}
		
		Pageable Pageable = new OffsetBasedPageRequest(Integer.parseInt(offset), Integer.parseInt(limit), sort);
		SearchCriteria searchCriteria = SearchUtils.generateSearchCriteriaObject(search);

		return ResponseEntity.ok(_usersAppService.find(searchCriteria,Pageable));
	}
    @PreAuthorize("hasAnyAuthority('USERSENTITY_READ')")
	@RequestMapping(value = "/{id}/timesheets", method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity getTimesheets(@PathVariable String id, @RequestParam(value="search", required=false) String search, @RequestParam(value = "offset", required=false) String offset, @RequestParam(value = "limit", required=false) String limit, Sort sort)throws Exception {
   		if (offset == null) { offset = env.getProperty("fastCode.offset.default"); }
		if (limit == null) { limit = env.getProperty("fastCode.limit.default"); }

		if(sort == null || sort.isEmpty()) {
			sort = Sort.by(Sort.Direction.ASC, "periodstartingdate");
		}
		
		Pageable pageable = new OffsetBasedPageRequest(Integer.parseInt(offset), Integer.parseInt(limit), sort);

		SearchCriteria searchCriteria = SearchUtils.generateSearchCriteriaObject(search);
		Map<String,String> joinColDetails=_usersAppService.parseTimesheetsJoinColumn(id);
		Optional.ofNullable(joinColDetails).orElseThrow(() -> new EntityNotFoundException(String.format("Invalid join column")));

		searchCriteria.setJoinColumns(joinColDetails);

    	List<FindTimesheetByIdOutput> output = _timesheetAppService.find(searchCriteria,pageable);
		Optional.ofNullable(output).orElseThrow(() -> new EntityNotFoundException(String.format("Not found")));

		return new ResponseEntity(output, HttpStatus.OK);
	}
    @PreAuthorize("hasAnyAuthority('USERSENTITY_READ')")
	@RequestMapping(value = "/{id}/usertasks", method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity getUsertasks(@PathVariable String id, @RequestParam(value="search", required=false) String search, @RequestParam(value = "offset", required=false) String offset, @RequestParam(value = "limit", required=false) String limit, Sort sort)throws Exception {
   		if (offset == null) { offset = env.getProperty("fastCode.offset.default"); }
		if (limit == null) { limit = env.getProperty("fastCode.limit.default"); }

		if(sort == null || sort.isEmpty()) {
			sort = Sort.by(Sort.Direction.ASC, "taskid");
		}
		
		Pageable pageable = new OffsetBasedPageRequest(Integer.parseInt(offset), Integer.parseInt(limit), sort);

		SearchCriteria searchCriteria = SearchUtils.generateSearchCriteriaObject(search);
		Map<String,String> joinColDetails=_usersAppService.parseUsertasksJoinColumn(id);
		Optional.ofNullable(joinColDetails).orElseThrow(() -> new EntityNotFoundException(String.format("Invalid join column")));

		searchCriteria.setJoinColumns(joinColDetails);

    	List<FindUsertaskByIdOutput> output = _usertaskAppService.find(searchCriteria,pageable);
		Optional.ofNullable(output).orElseThrow(() -> new EntityNotFoundException(String.format("Not found")));

		return new ResponseEntity(output, HttpStatus.OK);
	}

}


