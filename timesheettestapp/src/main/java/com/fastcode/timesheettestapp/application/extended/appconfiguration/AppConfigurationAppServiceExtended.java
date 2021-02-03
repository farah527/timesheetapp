package com.fastcode.timesheettestapp.application.extended.appconfiguration;

import org.springframework.stereotype.Service;
import com.fastcode.timesheettestapp.application.core.appconfiguration.AppConfigurationAppService;

import com.fastcode.timesheettestapp.domain.extended.appconfiguration.IAppConfigurationRepositoryExtended;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@Service("appConfigurationAppServiceExtended")
public class AppConfigurationAppServiceExtended extends AppConfigurationAppService implements IAppConfigurationAppServiceExtended {

	public AppConfigurationAppServiceExtended(IAppConfigurationRepositoryExtended appConfigurationRepositoryExtended,
				IAppConfigurationMapperExtended mapper,LoggingHelper logHelper) {

		super(appConfigurationRepositoryExtended,
		mapper,logHelper);

	}

 	//Add your custom code here
 
}

