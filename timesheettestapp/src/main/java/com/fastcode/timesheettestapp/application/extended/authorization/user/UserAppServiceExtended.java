package com.fastcode.timesheettestapp.application.extended.authorization.user;

import org.springframework.stereotype.Service;
import com.fastcode.timesheettestapp.application.core.authorization.user.UserAppService;

import com.fastcode.timesheettestapp.domain.extended.authorization.user.IUserRepositoryExtended;
import com.fastcode.timesheettestapp.domain.core.authorization.userpreference.IUserpreferenceRepository;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@Service("userAppServiceExtended")
public class UserAppServiceExtended extends UserAppService implements IUserAppServiceExtended {

	public UserAppServiceExtended(IUserRepositoryExtended userRepositoryExtended,
				IUserpreferenceRepository userpreferenceRepository,IUserMapperExtended mapper,LoggingHelper logHelper) {

		super(userRepositoryExtended,
		userpreferenceRepository,mapper,logHelper);

	}

 	//Add your custom code here
 
}

