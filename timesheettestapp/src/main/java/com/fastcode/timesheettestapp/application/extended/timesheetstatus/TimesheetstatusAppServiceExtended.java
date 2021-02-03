package com.fastcode.timesheettestapp.application.extended.timesheetstatus;

import org.springframework.stereotype.Service;
import com.fastcode.timesheettestapp.application.core.timesheetstatus.TimesheetstatusAppService;

import com.fastcode.timesheettestapp.domain.extended.timesheetstatus.ITimesheetstatusRepositoryExtended;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@Service("timesheetstatusAppServiceExtended")
public class TimesheetstatusAppServiceExtended extends TimesheetstatusAppService implements ITimesheetstatusAppServiceExtended {

	public TimesheetstatusAppServiceExtended(ITimesheetstatusRepositoryExtended timesheetstatusRepositoryExtended,
				ITimesheetstatusMapperExtended mapper,LoggingHelper logHelper) {

		super(timesheetstatusRepositoryExtended,
		mapper,logHelper);

	}

 	//Add your custom code here
 
}

