package com.fastcode.timesheettestapp.restcontrollers.extended;

import org.springframework.web.bind.annotation.*;
import com.fastcode.timesheettestapp.restcontrollers.core.TimeofftypeController;
import com.fastcode.timesheettestapp.application.extended.timeofftype.ITimeofftypeAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.timesheetdetails.ITimesheetdetailsAppServiceExtended;
import org.springframework.core.env.Environment;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@RestController
@RequestMapping("/timeofftype/extended")
public class TimeofftypeControllerExtended extends TimeofftypeController {

		public TimeofftypeControllerExtended(ITimeofftypeAppServiceExtended timeofftypeAppServiceExtended, ITimesheetdetailsAppServiceExtended timesheetdetailsAppServiceExtended,
	     LoggingHelper helper, Environment env) {
		super(
		timeofftypeAppServiceExtended,
    	timesheetdetailsAppServiceExtended,
		helper, env);
	}

	//Add your custom code here

}

