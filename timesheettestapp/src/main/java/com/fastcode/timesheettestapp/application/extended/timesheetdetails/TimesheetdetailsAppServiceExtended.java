package com.fastcode.timesheettestapp.application.extended.timesheetdetails;

import org.springframework.stereotype.Service;
import com.fastcode.timesheettestapp.application.core.timesheetdetails.TimesheetdetailsAppService;

import com.fastcode.timesheettestapp.domain.extended.timesheetdetails.ITimesheetdetailsRepositoryExtended;
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

	}

 	//Add your custom code here
 
}

