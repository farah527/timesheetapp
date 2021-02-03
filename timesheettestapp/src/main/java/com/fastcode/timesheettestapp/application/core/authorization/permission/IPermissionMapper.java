package com.fastcode.timesheettestapp.application.core.authorization.permission;

import org.mapstruct.Mapper;
import com.fastcode.timesheettestapp.application.core.authorization.permission.dto.*;
import com.fastcode.timesheettestapp.domain.core.authorization.permission.PermissionEntity;
import java.time.*;

@Mapper(componentModel = "spring")
public interface IPermissionMapper {

   PermissionEntity createPermissionInputToPermissionEntity(CreatePermissionInput permissionDto);
   CreatePermissionOutput permissionEntityToCreatePermissionOutput(PermissionEntity entity);
   
    PermissionEntity updatePermissionInputToPermissionEntity(UpdatePermissionInput permissionDto);
    
   	UpdatePermissionOutput permissionEntityToUpdatePermissionOutput(PermissionEntity entity);

   	FindPermissionByIdOutput permissionEntityToFindPermissionByIdOutput(PermissionEntity entity);


 	FindPermissionByNameOutput permissionEntityToFindPermissionByNameOutput(PermissionEntity entity);
 	
}

