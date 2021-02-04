package com.fastcode.timesheettestapp.application.extended.timesheetdetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fastcode.timesheettestapp.application.core.timesheetdetails.TimesheetdetailsAppService;
import com.fastcode.timesheettestapp.application.extended.timesheetdetails.dto.TimesheetdetailsInput;
import com.fastcode.timesheettestapp.domain.extended.timesheetdetails.ITimesheetdetailsRepositoryExtended;

import lombok.NonNull;

import com.fastcode.timesheettestapp.domain.core.task.TaskEntity;
import com.fastcode.timesheettestapp.domain.core.timeofftype.TimeofftypeEntity;
import com.fastcode.timesheettestapp.domain.core.timesheet.TimesheetEntity;
import com.fastcode.timesheettestapp.domain.core.timesheetdetails.TimesheetdetailsEntity;
import com.fastcode.timesheettestapp.domain.extended.task.ITaskRepositoryExtended;
import com.fastcode.timesheettestapp.domain.extended.timeofftype.ITimeofftypeRepositoryExtended;
import com.fastcode.timesheettestapp.domain.extended.timesheet.ITimesheetRepositoryExtended;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@Service("timesheetdetailsAppServiceExtended")
public class TimesheetdetailsAppServiceExtended extends TimesheetdetailsAppService implements ITimesheetdetailsAppServiceExtended {

	public TimesheetdetailsAppServiceExtended(ITimesheetdetailsRepositoryExtended timesheetdetailsRepositoryExtended,
				ITaskRepositoryExtended taskRepositoryExtended,ITimeofftypeRepositoryExtended timeofftypeRepositoryExtended,ITimesheetRepositoryExtended timesheetRepositoryExtended,ITimesheetdetailsMapperExtended mapper,LoggingHelper logHelper) {

		super(timesheetdetailsRepositoryExtended,
		taskRepositoryExtended,timeofftypeRepositoryExtended,timesheetRepositoryExtended,mapper,logHelper);

		this.extendedMapper = mapper;
		}

	 	//Add your custom code here
		
		@Qualifier("ITimesheetdetailsMapperExtendedImpl")
		@NonNull protected final ITimesheetdetailsMapperExtended extendedMapper;
	 
		@Transactional
		public Map<String, String> createMultipleDetails(List<TimesheetdetailsInput> timesheetdetailsList) {
			
			for(TimesheetdetailsInput input : timesheetdetailsList) {
			TaskEntity taskEntity = null;
			TimeofftypeEntity timeoffEntity = null;
			TimesheetEntity timesheetEntity = _timesheetRepository.findById(input.getTimesheetid()).orElse(null);
			if(timesheetEntity == null) {
				throw new EntityNotFoundException("Timesheet id is not valid");
			}
			
			if(input.getTaskid() !=null) {
			taskEntity = _taskRepository.findById(input.getTaskid()).orElse(null);
			if(taskEntity == null) {
				throw new EntityNotFoundException("Task id is not valid");
			}
			}
			
			if(input.getTimeofftypeid() !=null) {
			timeoffEntity = _timeofftypeRepository.findById(input.getTimeofftypeid()).orElse(null);
			if(timeoffEntity == null) {
				throw new EntityNotFoundException("Timeofftype id is not valid");
			}
			}
			
			TimesheetdetailsEntity details = extendedMapper.timesheetdetailsInputToTimesheetdetailsEntity(input);
			details.setTask(taskEntity);
			details.setTimeofftype(timeoffEntity);
			details.setTimesheet(timesheetEntity);
			
			if(details.getId() != null) {
				TimesheetdetailsEntity entity = _timesheetdetailsRepository.findById(details.getId()).get();
				if(entity !=null) {
				details.setVersiono(entity.getVersiono());
				}
			}
			_timesheetdetailsRepository.save(details);
			
			}
			
			Map<String,String> response = new HashMap<String, String>();
			response.put("msg", "Timesheet details Successfully added");
			
			return response;
		}
	 
 
}

