package com.fastcode.timesheettestapp.restcontrollers.extended;

import org.springframework.web.bind.annotation.*;
import com.fastcode.timesheettestapp.restcontrollers.core.UserroleController;
import com.fastcode.timesheettestapp.application.extended.authorization.userrole.IUserroleAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.authorization.role.IRoleAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.authorization.user.IUserAppServiceExtended;
import com.fastcode.timesheettestapp.security.JWTAppService;
import org.springframework.core.env.Environment;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@RestController
@RequestMapping("/userrole/extended")
public class UserroleControllerExtended extends UserroleController {

		public UserroleControllerExtended(IUserroleAppServiceExtended userroleAppServiceExtended, IRoleAppServiceExtended roleAppServiceExtended, IUserAppServiceExtended userAppServiceExtended,
	    JWTAppService jwtAppService, LoggingHelper helper, Environment env) {
		super(
		userroleAppServiceExtended,
    	roleAppServiceExtended,
    	userAppServiceExtended,
	    jwtAppService,
		helper, env);
	}

	//Add your custom code here

}

