package com.fastcode.timesheettestapp.restcontrollers.extended;

import org.springframework.web.bind.annotation.*;
import com.fastcode.timesheettestapp.restcontrollers.core.TimesheetController;

import lombok.NonNull;

import com.fastcode.timesheettestapp.application.extended.appconfiguration.IAppConfigurationAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.timesheet.ITimesheetAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.timesheetdetails.ITimesheetdetailsAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.timesheetdetails.dto.TimesheetdetailsInput;
import com.fastcode.timesheettestapp.application.extended.timesheetstatus.ITimesheetstatusAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.users.IUsersAppServiceExtended;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;
import com.fastcode.timesheettestapp.domain.core.appconfiguration.AppConfigurationEntity;

@RestController
@RequestMapping("/timesheet/extended")
public class TimesheetControllerExtended extends TimesheetController {

		public TimesheetControllerExtended(ITimesheetAppServiceExtended timesheetAppServiceExtended, ITimesheetdetailsAppServiceExtended timesheetdetailsAppServiceExtended, ITimesheetstatusAppServiceExtended timesheetstatusAppServiceExtended, IUsersAppServiceExtended usersAppServiceExtended,
	     LoggingHelper helper, Environment env, IAppConfigurationAppServiceExtended appConfExtended) {
		super(
		timesheetAppServiceExtended,
    	timesheetdetailsAppServiceExtended,
    	timesheetstatusAppServiceExtended,
    	usersAppServiceExtended,
		helper, env);
		
		this._timesheetdetailsAppServiceExtended = timesheetdetailsAppServiceExtended;
		this._timesheetAppServiceExtended = timesheetAppServiceExtended;
		this._appConfExtended = appConfExtended;
	}

	//Add your custom code here

	@Qualifier("timesheetdetailsExtendedAppService")
	@NonNull  protected final ITimesheetdetailsAppServiceExtended  _timesheetdetailsAppServiceExtended;
	
	@Qualifier("timesheetExtendedAppService")
	@NonNull  protected final ITimesheetAppServiceExtended  _timesheetAppServiceExtended;
	
	@Qualifier("appConfigurationAppServiceExtended")
	@NonNull  protected final IAppConfigurationAppServiceExtended _appConfExtended;
	
	@RequestMapping(value="/timesheetdetails", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<Map<String,String>> createMultipleDetails(@RequestBody @Valid List<TimesheetdetailsInput> inputList) {

		return new ResponseEntity(_timesheetdetailsAppServiceExtended.createMultipleDetails(inputList), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getValueByType", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Map<String,String>> getValue(@RequestParam("type") String type) {

		AppConfigurationEntity appConf = _appConfExtended.findByType(type);
		if(appConf != null) {
		Map<String, String> response = new HashMap<String,String>();
		response.put(appConf.getType(), appConf.getValue());
		return new ResponseEntity(response, HttpStatus.OK);
		}
		else 
			throw new EntityNotFoundException("Entity not exists with type " + type);
		//return new ResponseEntity(_timesheetdetailsAppServiceExtended.createMultipleDetails(inputList), HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateYear", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Map<String,String>> updateYear(@RequestParam("value") String value) {

		AppConfigurationEntity appConf = _appConfExtended.updateYear(value);
		if(appConf != null) {
		Map<String, String> response = new HashMap<String,String>();
		response.put(appConf.getType(), appConf.getValue());
		return new ResponseEntity(response, HttpStatus.OK);
		}
		else 
			throw new EntityNotFoundException("Entity not exists with type year");
		//return new ResponseEntity(_timesheetdetailsAppServiceExtended.createMultipleDetails(inputList), HttpStatus.OK);
	}

}

