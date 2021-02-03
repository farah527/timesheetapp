package com.fastcode.timesheettestapp.application.core.authorization.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ForgotPasswordInput {
	private String email;
	private String clientUrl;
}
