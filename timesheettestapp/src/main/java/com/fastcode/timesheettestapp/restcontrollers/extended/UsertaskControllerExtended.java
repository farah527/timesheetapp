package com.fastcode.timesheettestapp.restcontrollers.extended;

import org.springframework.web.bind.annotation.*;
import com.fastcode.timesheettestapp.restcontrollers.core.UsertaskController;
import com.fastcode.timesheettestapp.application.extended.usertask.IUsertaskAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.task.ITaskAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.users.IUsersAppServiceExtended;
import org.springframework.core.env.Environment;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@RestController
@RequestMapping("/usertask/extended")
public class UsertaskControllerExtended extends UsertaskController {

		public UsertaskControllerExtended(IUsertaskAppServiceExtended usertaskAppServiceExtended, ITaskAppServiceExtended taskAppServiceExtended, IUsersAppServiceExtended usersAppServiceExtended,
	     LoggingHelper helper, Environment env) {
		super(
		usertaskAppServiceExtended,
    	taskAppServiceExtended,
    	usersAppServiceExtended,
		helper, env);
	}

	//Add your custom code here

}

