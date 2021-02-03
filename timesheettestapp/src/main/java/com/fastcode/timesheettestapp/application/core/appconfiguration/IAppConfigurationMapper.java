package com.fastcode.timesheettestapp.application.core.appconfiguration;

import org.mapstruct.Mapper;
import com.fastcode.timesheettestapp.application.core.appconfiguration.dto.*;
import com.fastcode.timesheettestapp.domain.core.appconfiguration.AppConfigurationEntity;
import java.time.*;

@Mapper(componentModel = "spring")
public interface IAppConfigurationMapper {

   AppConfigurationEntity createAppConfigurationInputToAppConfigurationEntity(CreateAppConfigurationInput appconfigurationDto);
   CreateAppConfigurationOutput appConfigurationEntityToCreateAppConfigurationOutput(AppConfigurationEntity entity);
   
    AppConfigurationEntity updateAppConfigurationInputToAppConfigurationEntity(UpdateAppConfigurationInput appConfigurationDto);
    
   	UpdateAppConfigurationOutput appConfigurationEntityToUpdateAppConfigurationOutput(AppConfigurationEntity entity);

   	FindAppConfigurationByIdOutput appConfigurationEntityToFindAppConfigurationByIdOutput(AppConfigurationEntity entity);


}

