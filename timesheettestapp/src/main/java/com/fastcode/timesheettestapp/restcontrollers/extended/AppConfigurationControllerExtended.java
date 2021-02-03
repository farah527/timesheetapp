package com.fastcode.timesheettestapp.restcontrollers.extended;

import org.springframework.web.bind.annotation.*;
import com.fastcode.timesheettestapp.restcontrollers.core.AppConfigurationController;
import com.fastcode.timesheettestapp.application.extended.appconfiguration.IAppConfigurationAppServiceExtended;
import org.springframework.core.env.Environment;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@RestController
@RequestMapping("/appConfiguration/extended")
public class AppConfigurationControllerExtended extends AppConfigurationController {

		public AppConfigurationControllerExtended(IAppConfigurationAppServiceExtended appConfigurationAppServiceExtended,
	     LoggingHelper helper, Environment env) {
		super(
		appConfigurationAppServiceExtended,
		helper, env);
	}

	//Add your custom code here

}

