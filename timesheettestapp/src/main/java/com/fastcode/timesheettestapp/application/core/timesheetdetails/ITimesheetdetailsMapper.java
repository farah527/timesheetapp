package com.fastcode.timesheettestapp.application.core.timesheetdetails;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.fastcode.timesheettestapp.domain.core.task.TaskEntity;
import com.fastcode.timesheettestapp.domain.core.timeofftype.TimeofftypeEntity;
import com.fastcode.timesheettestapp.domain.core.timesheet.TimesheetEntity;
import com.fastcode.timesheettestapp.application.core.timesheetdetails.dto.*;
import com.fastcode.timesheettestapp.domain.core.timesheetdetails.TimesheetdetailsEntity;
import java.time.*;

@Mapper(componentModel = "spring")
public interface ITimesheetdetailsMapper {

   TimesheetdetailsEntity createTimesheetdetailsInputToTimesheetdetailsEntity(CreateTimesheetdetailsInput timesheetdetailsDto);
   
   @Mappings({ 
   @Mapping(source = "entity.task.id", target = "taskid"),                   
   @Mapping(source = "entity.task.name", target = "taskDescriptiveField"),                    
   @Mapping(source = "entity.timeofftype.id", target = "timeofftypeid"),                   
   @Mapping(source = "entity.timeofftype.typename", target = "timeofftypeDescriptiveField"),                    
   @Mapping(source = "entity.timesheet.id", target = "timesheetid"),                   
   @Mapping(source = "entity.timesheet.periodstartingdate", target = "timesheetDescriptiveField"),                    
   }) 
   CreateTimesheetdetailsOutput timesheetdetailsEntityToCreateTimesheetdetailsOutput(TimesheetdetailsEntity entity);
   
    TimesheetdetailsEntity updateTimesheetdetailsInputToTimesheetdetailsEntity(UpdateTimesheetdetailsInput timesheetdetailsDto);
    
    @Mappings({ 
    @Mapping(source = "entity.task.id", target = "taskid"),                   
    @Mapping(source = "entity.task.name", target = "taskDescriptiveField"),                    
    @Mapping(source = "entity.timeofftype.id", target = "timeofftypeid"),                   
    @Mapping(source = "entity.timeofftype.typename", target = "timeofftypeDescriptiveField"),                    
    @Mapping(source = "entity.timesheet.id", target = "timesheetid"),                   
    @Mapping(source = "entity.timesheet.periodstartingdate", target = "timesheetDescriptiveField"),                    
   	}) 
   	UpdateTimesheetdetailsOutput timesheetdetailsEntityToUpdateTimesheetdetailsOutput(TimesheetdetailsEntity entity);

   	@Mappings({ 
   	@Mapping(source = "entity.task.id", target = "taskid"),                   
   	@Mapping(source = "entity.task.name", target = "taskDescriptiveField"),                    
   	@Mapping(source = "entity.timeofftype.id", target = "timeofftypeid"),                   
   	@Mapping(source = "entity.timeofftype.typename", target = "timeofftypeDescriptiveField"),                    
   	@Mapping(source = "entity.timesheet.id", target = "timesheetid"),                   
   	@Mapping(source = "entity.timesheet.periodstartingdate", target = "timesheetDescriptiveField"),                    
   	}) 
   	FindTimesheetdetailsByIdOutput timesheetdetailsEntityToFindTimesheetdetailsByIdOutput(TimesheetdetailsEntity entity);


   @Mappings({
   @Mapping(source = "task.id", target = "id"),                  
   @Mapping(source = "foundTimesheetdetails.id", target = "timesheetdetailsId"),
   })
   GetTaskOutput taskEntityToGetTaskOutput(TaskEntity task, TimesheetdetailsEntity foundTimesheetdetails);
   
   @Mappings({
   @Mapping(source = "timesheet.id", target = "id"),                  
   @Mapping(source = "timesheet.notes", target = "notes"),                  
   @Mapping(source = "foundTimesheetdetails.id", target = "timesheetdetailsId"),
   })
   GetTimesheetOutput timesheetEntityToGetTimesheetOutput(TimesheetEntity timesheet, TimesheetdetailsEntity foundTimesheetdetails);
   
   @Mappings({
   @Mapping(source = "timeofftype.id", target = "id"),                  
   @Mapping(source = "foundTimesheetdetails.id", target = "timesheetdetailsId"),
   })
   GetTimeofftypeOutput timeofftypeEntityToGetTimeofftypeOutput(TimeofftypeEntity timeofftype, TimesheetdetailsEntity foundTimesheetdetails);
   
}

