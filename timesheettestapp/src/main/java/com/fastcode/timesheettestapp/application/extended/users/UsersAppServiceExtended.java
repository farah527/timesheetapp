package com.fastcode.timesheettestapp.application.extended.users;

import org.springframework.stereotype.Service;
import com.fastcode.timesheettestapp.application.core.users.UsersAppService;

import com.fastcode.timesheettestapp.domain.extended.users.IUsersRepositoryExtended;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@Service("usersAppServiceExtended")
public class UsersAppServiceExtended extends UsersAppService implements IUsersAppServiceExtended {

	public UsersAppServiceExtended(IUsersRepositoryExtended usersRepositoryExtended,
				IUsersMapperExtended mapper,LoggingHelper logHelper) {

		super(usersRepositoryExtended,
		mapper,logHelper);

	}

 	//Add your custom code here
 
}

