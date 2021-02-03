package com.fastcode.timesheettestapp.restcontrollers.extended;

import org.springframework.web.bind.annotation.*;
import com.fastcode.timesheettestapp.restcontrollers.core.TimesheetdetailsController;
import com.fastcode.timesheettestapp.application.extended.timesheetdetails.ITimesheetdetailsAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.task.ITaskAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.timeofftype.ITimeofftypeAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.timesheet.ITimesheetAppServiceExtended;
import org.springframework.core.env.Environment;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@RestController
@RequestMapping("/timesheetdetails/extended")
public class TimesheetdetailsControllerExtended extends TimesheetdetailsController {

		public TimesheetdetailsControllerExtended(ITimesheetdetailsAppServiceExtended timesheetdetailsAppServiceExtended, ITaskAppServiceExtended taskAppServiceExtended, ITimeofftypeAppServiceExtended timeofftypeAppServiceExtended, ITimesheetAppServiceExtended timesheetAppServiceExtended,
	     LoggingHelper helper, Environment env) {
		super(
		timesheetdetailsAppServiceExtended,
    	taskAppServiceExtended,
    	timeofftypeAppServiceExtended,
    	timesheetAppServiceExtended,
		helper, env);
	}

	//Add your custom code here

}

