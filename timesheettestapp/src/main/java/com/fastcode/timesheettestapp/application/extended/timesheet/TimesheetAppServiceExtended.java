package com.fastcode.timesheettestapp.application.extended.timesheet;

import org.springframework.stereotype.Service;
import com.fastcode.timesheettestapp.application.core.timesheet.TimesheetAppService;
import com.fastcode.timesheettestapp.domain.extended.timesheet.ITimesheetRepositoryExtended;
import com.fastcode.timesheettestapp.domain.extended.timesheetstatus.ITimesheetstatusRepositoryExtended;
import com.fastcode.timesheettestapp.domain.extended.users.IUsersRepositoryExtended;

import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@Service("timesheetAppServiceExtended")
public class TimesheetAppServiceExtended extends TimesheetAppService implements ITimesheetAppServiceExtended {

	public TimesheetAppServiceExtended(ITimesheetRepositoryExtended timesheetRepositoryExtended,
				ITimesheetstatusRepositoryExtended timesheetstatusRepositoryExtended,IUsersRepositoryExtended usersRepositoryExtended,ITimesheetMapperExtended mapper,LoggingHelper logHelper) {

		super(timesheetRepositoryExtended,
		timesheetstatusRepositoryExtended,usersRepositoryExtended,mapper,logHelper);

		
	}
 
}

