package com.fastcode.timesheettestapp.application.core.users;

import org.mapstruct.Mapper;
import com.fastcode.timesheettestapp.application.core.users.dto.*;
import com.fastcode.timesheettestapp.domain.core.users.UsersEntity;
import java.time.*;

@Mapper(componentModel = "spring")
public interface IUsersMapper {

   UsersEntity createUsersInputToUsersEntity(CreateUsersInput usersDto);
   CreateUsersOutput usersEntityToCreateUsersOutput(UsersEntity entity);
   
    UsersEntity updateUsersInputToUsersEntity(UpdateUsersInput usersDto);
    
   	UpdateUsersOutput usersEntityToUpdateUsersOutput(UsersEntity entity);

   	FindUsersByIdOutput usersEntityToFindUsersByIdOutput(UsersEntity entity);


}

