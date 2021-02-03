package com.fastcode.timesheettestapp.application.extended.project;

import org.springframework.stereotype.Service;
import com.fastcode.timesheettestapp.application.core.project.ProjectAppService;

import com.fastcode.timesheettestapp.domain.extended.project.IProjectRepositoryExtended;
import com.fastcode.timesheettestapp.domain.extended.customer.ICustomerRepositoryExtended;
import com.fastcode.timesheettestapp.commons.logging.LoggingHelper;

@Service("projectAppServiceExtended")
public class ProjectAppServiceExtended extends ProjectAppService implements IProjectAppServiceExtended {

	public ProjectAppServiceExtended(IProjectRepositoryExtended projectRepositoryExtended,
				ICustomerRepositoryExtended customerRepositoryExtended,IProjectMapperExtended mapper,LoggingHelper logHelper) {

		super(projectRepositoryExtended,
		customerRepositoryExtended,mapper,logHelper);

	}

 	//Add your custom code here
 
}

