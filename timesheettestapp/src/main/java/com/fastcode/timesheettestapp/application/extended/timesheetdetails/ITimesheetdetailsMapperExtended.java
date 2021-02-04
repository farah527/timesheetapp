package com.fastcode.timesheettestapp.application.extended.timesheetdetails;

import org.mapstruct.Mapper;
import com.fastcode.timesheettestapp.application.core.timesheetdetails.ITimesheetdetailsMapper;
import com.fastcode.timesheettestapp.application.extended.timesheetdetails.dto.TimesheetdetailsInput;
import com.fastcode.timesheettestapp.domain.core.timesheetdetails.TimesheetdetailsEntity;

@Mapper(componentModel = "spring")
public interface ITimesheetdetailsMapperExtended extends ITimesheetdetailsMapper {

	TimesheetdetailsEntity timesheetdetailsInputToTimesheetdetailsEntity(TimesheetdetailsInput timesheetdetailsDto);
	
}

