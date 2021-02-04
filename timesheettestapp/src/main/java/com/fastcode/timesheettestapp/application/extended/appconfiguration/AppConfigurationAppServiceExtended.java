package com.fastcode.timesheettestapp.application.extended.appconfiguration;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.fastcode.timesheettestapp.application.core.appconfiguration.AppConfigurationAppService;
import com.fastcode.timesheettestapp.domain.core.appconfiguration.AppConfigurationEntity;
import com.fastcode.timesheettestapp.domain.extended.appconfiguration.IAppConfigurationRepositoryExtended;

import lombok.NonNull;

import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@Service("appConfigurationAppServiceExtended")
public class AppConfigurationAppServiceExtended extends AppConfigurationAppService implements IAppConfigurationAppServiceExtended {

	public AppConfigurationAppServiceExtended(IAppConfigurationRepositoryExtended appConfigurationRepositoryExtended,
				IAppConfigurationMapperExtended mapper,LoggingHelper logHelper) {

		super(appConfigurationRepositoryExtended,
		mapper,logHelper);

		this._appConfigurationRepositoryExtended = appConfigurationRepositoryExtended;
	}
	
	@Qualifier("appConfigurationRepositoryExtended")
	@NonNull  protected final IAppConfigurationRepositoryExtended  _appConfigurationRepositoryExtended;
	
	@Transactional
	public AppConfigurationEntity findByType(String type) {
		
		AppConfigurationEntity appConf = _appConfigurationRepositoryExtended.findByType(type);
		return appConf;
	}
	
	@Transactional
	public AppConfigurationEntity updateYear(String value) {
		
		AppConfigurationEntity appConf = _appConfigurationRepositoryExtended.findByType("year");
		if(appConf == null) {
			return null;
		}
		else
		{
			appConf.setValue(value);
			appConf = _appConfigurationRepositoryExtended.save(appConf);
		}
		return appConf;
	}

 	//Add your custom code here
 
}

