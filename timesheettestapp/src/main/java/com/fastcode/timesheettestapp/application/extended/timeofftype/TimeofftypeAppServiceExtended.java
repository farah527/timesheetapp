package com.fastcode.timesheettestapp.application.extended.timeofftype;

import org.springframework.stereotype.Service;
import com.fastcode.timesheettestapp.application.core.timeofftype.TimeofftypeAppService;

import com.fastcode.timesheettestapp.domain.extended.timeofftype.ITimeofftypeRepositoryExtended;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@Service("timeofftypeAppServiceExtended")
public class TimeofftypeAppServiceExtended extends TimeofftypeAppService implements ITimeofftypeAppServiceExtended {

	public TimeofftypeAppServiceExtended(ITimeofftypeRepositoryExtended timeofftypeRepositoryExtended,
				ITimeofftypeMapperExtended mapper,LoggingHelper logHelper) {

		super(timeofftypeRepositoryExtended,
		mapper,logHelper);

	}

 	//Add your custom code here
 
}

