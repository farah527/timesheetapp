package com.fastcode.timesheettestapp.restcontrollers.extended;

import org.springframework.web.bind.annotation.*;
import com.fastcode.timesheettestapp.restcontrollers.core.UsersController;
import com.fastcode.timesheettestapp.application.extended.users.IUsersAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.timesheet.ITimesheetAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.usertask.IUsertaskAppServiceExtended;
import org.springframework.core.env.Environment;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@RestController
@RequestMapping("/users/extended")
public class UsersControllerExtended extends UsersController {

		public UsersControllerExtended(IUsersAppServiceExtended usersAppServiceExtended, ITimesheetAppServiceExtended timesheetAppServiceExtended, IUsertaskAppServiceExtended usertaskAppServiceExtended,
	     LoggingHelper helper, Environment env) {
		super(
		usersAppServiceExtended,
    	timesheetAppServiceExtended,
    	usertaskAppServiceExtended,
		helper, env);
	}

	//Add your custom code here

}

