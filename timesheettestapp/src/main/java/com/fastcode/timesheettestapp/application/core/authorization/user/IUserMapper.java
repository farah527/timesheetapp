package com.fastcode.timesheettestapp.application.core.authorization.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.fastcode.timesheettestapp.domain.core.authorization.userpreference.UserpreferenceEntity;
import com.fastcode.timesheettestapp.application.core.authorization.user.dto.*;
import com.fastcode.timesheettestapp.domain.core.authorization.user.UserEntity;
import java.time.*;

@Mapper(componentModel = "spring")
public interface IUserMapper {

   UserEntity createUserInputToUserEntity(CreateUserInput userDto);
   
   @Mappings({
   @Mapping(source = "entity.id", target = "id"),
   })
   CreateUserOutput userEntityToCreateUserOutput(UserEntity entity,UserpreferenceEntity userPreference);
   
    @Mappings({
    @Mapping(source = "userProfile.userName", target = "userName"),
    @Mapping(source = "userProfile.emailAddress", target = "emailAddress"),
    @Mapping(source = "userProfile.phoneNumber", target = "phoneNumber"),
    @Mapping(source = "userProfile.lastName", target = "lastName"),
    @Mapping(source = "userProfile.firstName", target = "firstName")
    })
    UpdateUserInput findUserWithAllFieldsByIdOutputAndUserProfileToUpdateUserInput(FindUserWithAllFieldsByIdOutput user, UserProfile userProfile);
    
    UserEntity findUserWithAllFieldsByIdOutputToUserEntity(FindUserWithAllFieldsByIdOutput user);
    
    UserProfile updateUserOutputToUserProfile(UpdateUserOutput userDto);
    
    UserProfile findUserByIdOutputToUserProfile(FindUserByIdOutput user);
    
    UserEntity updateUserInputToUserEntity(UpdateUserInput userDto);
    
   	UpdateUserOutput userEntityToUpdateUserOutput(UserEntity entity);

   	@Mappings({
    @Mapping(source = "entity.versiono", target = "versiono"),
    @Mapping(source = "entity.id", target = "id"),
   	})
   	FindUserByIdOutput userEntityToFindUserByIdOutput(UserEntity entity,UserpreferenceEntity userPreference);

   	FindUserWithAllFieldsByIdOutput userEntityToFindUserWithAllFieldsByIdOutput(UserEntity entity);
	FindUserByNameOutput userEntityToFindUserByNameOutput(UserEntity entity);

}

