package com.popa.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//Spring to manage this and instance it (Service or Component)
@Component
@Scope("prototype")
/**by default the scope is singleton. 
 * prototype - will create each time a new class instance.
 * request
 * session
*/
public class WelcomeService {
	public String getWelcomeMessage() {
		return "Good Morning";
	}
	
	@PostConstruct
	public void init() {
		System.out.println("Method called when the Welcome service is created");
	}
	
	@PreDestroy
	public void destroy() {
	   System.out.println("Method call ed when Welcome service is destroyed");
	}
}