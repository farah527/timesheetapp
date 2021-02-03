package com.fastcode.timesheettestapp.application.extended.customer;

import org.springframework.stereotype.Service;
import com.fastcode.timesheettestapp.application.core.customer.CustomerAppService;

import com.fastcode.timesheettestapp.domain.extended.customer.ICustomerRepositoryExtended;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@Service("customerAppServiceExtended")
public class CustomerAppServiceExtended extends CustomerAppService implements ICustomerAppServiceExtended {

	public CustomerAppServiceExtended(ICustomerRepositoryExtended customerRepositoryExtended,
				ICustomerMapperExtended mapper,LoggingHelper logHelper) {

		super(customerRepositoryExtended,
		mapper,logHelper);

	}

 	//Add your custom code here
 
}

