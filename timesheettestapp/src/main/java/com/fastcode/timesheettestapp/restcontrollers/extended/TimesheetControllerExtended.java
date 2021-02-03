package com.fastcode.timesheettestapp.restcontrollers.extended;

import org.springframework.web.bind.annotation.*;
import com.fastcode.timesheettestapp.restcontrollers.core.TimesheetController;
import com.fastcode.timesheettestapp.application.extended.timesheet.ITimesheetAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.timesheetdetails.ITimesheetdetailsAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.timesheetstatus.ITimesheetstatusAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.users.IUsersAppServiceExtended;
import org.springframework.core.env.Environment;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@RestController
@RequestMapping("/timesheet/extended")
public class TimesheetControllerExtended extends TimesheetController {

		public TimesheetControllerExtended(ITimesheetAppServiceExtended timesheetAppServiceExtended, ITimesheetdetailsAppServiceExtended timesheetdetailsAppServiceExtended, ITimesheetstatusAppServiceExtended timesheetstatusAppServiceExtended, IUsersAppServiceExtended usersAppServiceExtended,
	     LoggingHelper helper, Environment env) {
		super(
		timesheetAppServiceExtended,
    	timesheetdetailsAppServiceExtended,
    	timesheetstatusAppServiceExtended,
    	usersAppServiceExtended,
		helper, env);
	}

	//Add your custom code here

}

