package com.fastcode.timesheettestapp.domain.extended.appconfiguration;

import org.springframework.stereotype.Repository;

import com.fastcode.timesheettestapp.domain.core.appconfiguration.AppConfigurationEntity;
import com.fastcode.timesheettestapp.domain.core.appconfiguration.IAppConfigurationRepository;
@Repository("appConfigurationRepositoryExtended")
public interface IAppConfigurationRepositoryExtended extends IAppConfigurationRepository {

	//Add your custom code here
	AppConfigurationEntity findByType(String type);
}

