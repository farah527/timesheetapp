package com.fastcode.timesheettestapp.application.core.timeofftype;

import org.mapstruct.Mapper;
import com.fastcode.timesheettestapp.application.core.timeofftype.dto.*;
import com.fastcode.timesheettestapp.domain.core.timeofftype.TimeofftypeEntity;
import java.time.*;

@Mapper(componentModel = "spring")
public interface ITimeofftypeMapper {

   TimeofftypeEntity createTimeofftypeInputToTimeofftypeEntity(CreateTimeofftypeInput timeofftypeDto);
   CreateTimeofftypeOutput timeofftypeEntityToCreateTimeofftypeOutput(TimeofftypeEntity entity);
   
    TimeofftypeEntity updateTimeofftypeInputToTimeofftypeEntity(UpdateTimeofftypeInput timeofftypeDto);
    
   	UpdateTimeofftypeOutput timeofftypeEntityToUpdateTimeofftypeOutput(TimeofftypeEntity entity);

   	FindTimeofftypeByIdOutput timeofftypeEntityToFindTimeofftypeByIdOutput(TimeofftypeEntity entity);


}

