package com.fastcode.timesheettestapp.application.extended.usertask;

import org.springframework.stereotype.Service;
import com.fastcode.timesheettestapp.application.core.usertask.UsertaskAppService;

import com.fastcode.timesheettestapp.domain.extended.usertask.IUsertaskRepositoryExtended;
import com.fastcode.timesheettestapp.domain.extended.task.ITaskRepositoryExtended;
import com.fastcode.timesheettestapp.domain.extended.users.IUsersRepositoryExtended;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@Service("usertaskAppServiceExtended")
public class UsertaskAppServiceExtended extends UsertaskAppService implements IUsertaskAppServiceExtended {

	public UsertaskAppServiceExtended(IUsertaskRepositoryExtended usertaskRepositoryExtended,
				ITaskRepositoryExtended taskRepositoryExtended,IUsersRepositoryExtended usersRepositoryExtended,IUsertaskMapperExtended mapper,LoggingHelper logHelper) {

		super(usertaskRepositoryExtended,
		taskRepositoryExtended,usersRepositoryExtended,mapper,logHelper);

	}

 	//Add your custom code here
 
}

