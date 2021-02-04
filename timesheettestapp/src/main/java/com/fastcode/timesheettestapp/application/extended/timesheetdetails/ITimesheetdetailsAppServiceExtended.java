package com.fastcode.timesheettestapp.application.extended.timesheetdetails;

import java.util.List;
import java.util.Map;
import com.fastcode.timesheettestapp.application.core.timesheetdetails.ITimesheetdetailsAppService;
import com.fastcode.timesheettestapp.application.extended.timesheetdetails.dto.TimesheetdetailsInput;

public interface ITimesheetdetailsAppServiceExtended extends ITimesheetdetailsAppService {

	//Add your custom code here
	public Map<String, String> createMultipleDetails(List<TimesheetdetailsInput> timesheetdetailsList);
		
}
