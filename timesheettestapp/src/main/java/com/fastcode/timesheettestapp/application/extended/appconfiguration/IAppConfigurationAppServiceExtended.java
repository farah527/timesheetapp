package com.fastcode.timesheettestapp.application.extended.appconfiguration;

import com.fastcode.timesheettestapp.application.core.appconfiguration.IAppConfigurationAppService;
import com.fastcode.timesheettestapp.domain.core.appconfiguration.AppConfigurationEntity;

public interface IAppConfigurationAppServiceExtended extends IAppConfigurationAppService {

	//Add your custom code here
	public AppConfigurationEntity findByType(String type);
	
	public AppConfigurationEntity updateYear(String value);
}
