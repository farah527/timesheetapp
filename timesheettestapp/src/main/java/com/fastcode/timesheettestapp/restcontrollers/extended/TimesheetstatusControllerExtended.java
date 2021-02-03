package com.fastcode.timesheettestapp.restcontrollers.extended;

import org.springframework.web.bind.annotation.*;
import com.fastcode.timesheettestapp.restcontrollers.core.TimesheetstatusController;
import com.fastcode.timesheettestapp.application.extended.timesheetstatus.ITimesheetstatusAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.timesheet.ITimesheetAppServiceExtended;
import org.springframework.core.env.Environment;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@RestController
@RequestMapping("/timesheetstatus/extended")
public class TimesheetstatusControllerExtended extends TimesheetstatusController {

		public TimesheetstatusControllerExtended(ITimesheetstatusAppServiceExtended timesheetstatusAppServiceExtended, ITimesheetAppServiceExtended timesheetAppServiceExtended,
	     LoggingHelper helper, Environment env) {
		super(
		timesheetstatusAppServiceExtended,
    	timesheetAppServiceExtended,
		helper, env);
	}

	//Add your custom code here

}

