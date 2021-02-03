package com.fastcode.timesheettestapp.restcontrollers.extended;

import org.springframework.web.bind.annotation.*;
import com.fastcode.timesheettestapp.restcontrollers.core.CustomerController;
import com.fastcode.timesheettestapp.application.extended.customer.ICustomerAppServiceExtended;
import com.fastcode.timesheettestapp.application.extended.project.IProjectAppServiceExtended;
import org.springframework.core.env.Environment;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@RestController
@RequestMapping("/customer/extended")
public class CustomerControllerExtended extends CustomerController {

		public CustomerControllerExtended(ICustomerAppServiceExtended customerAppServiceExtended, IProjectAppServiceExtended projectAppServiceExtended,
	     LoggingHelper helper, Environment env) {
		super(
		customerAppServiceExtended,
    	projectAppServiceExtended,
		helper, env);
	}

	//Add your custom code here

}

