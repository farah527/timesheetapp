package com.fastcode.timesheettestapp.application.core.users.dto;

import java.time.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUsersInput {

  	@NotNull(message = "emailaddress Should not be null")
  	@Length(max = 255, message = "emailaddress must be less than 255 characters")
  	private String emailaddress;
  
  	@NotNull(message = "firstname Should not be null")
  	@Length(max = 255, message = "firstname must be less than 255 characters")
  	private String firstname;
  
  	@NotNull(message = "isactive Should not be null")
  	private Boolean isactive;
  
  	@NotNull(message = "isemailconfirmed Should not be null")
  	private Boolean isemailconfirmed;
  
  	private LocalDate joinDate;
  
  	@NotNull(message = "lastname Should not be null")
  	@Length(max = 255, message = "lastname must be less than 255 characters")
  	private String lastname;
  
  	@NotNull(message = "password Should not be null")
  	@Length(max = 255, message = "password must be less than 255 characters")
  	private String password;
  
  	@NotNull(message = "username Should not be null")
  	@Length(max = 255, message = "username must be less than 255 characters")
  	private String username;
  

}

