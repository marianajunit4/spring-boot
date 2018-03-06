package com.popa.service;

import org.springframework.stereotype.Component;

//Spring to manage this and instance it (Service or Component)
@Component
public class WelcomeService {
	public String getWelcomeMessage() {
		return "Good Morning";
	}
}