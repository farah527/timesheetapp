package com.fastcode.timesheettestapp.restcontrollers.extended;

import org.springframework.web.bind.annotation.*;
import com.fastcode.timesheettestapp.restcontrollers.core.UserController;
import com.fastcode.timesheettestapp.application.extended.authorization.user.IUserAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.authorization.userpermission.IUserpermissionAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.authorization.userrole.IUserroleAppServiceExtended;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fastcode.timesheettestapp.security.JWTAppService;
import org.springframework.core.env.Environment;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@RestController
@RequestMapping("/user/extended")
public class UserControllerExtended extends UserController {

		public UserControllerExtended(IUserAppServiceExtended userAppServiceExtended, IUserpermissionAppServiceExtended userpermissionAppServiceExtended, IUserroleAppServiceExtended userroleAppServiceExtended,
	    PasswordEncoder pEncoder,JWTAppService jwtAppService, LoggingHelper helper, Environment env) {
		super(
		userAppServiceExtended,
    	userpermissionAppServiceExtended,
    	userroleAppServiceExtended,
	    pEncoder,
	    jwtAppService,
		helper, env);
	}

	//Add your custom code here

}

