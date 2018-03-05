package com.popa.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.popa.service.WelcomeService;

//(the spring will scan the restcontroller classes, will instance the WelcomeController class and will see that
// it need the WelcomeService class and will create the bean and will autowire in there, after that will launch the
// application
@RestController
public class WelcomeController {
	
	//Dependencies (spring creates the beans and inject them where are needed
	@Autowired
	private WelcomeService service;
	
	@RequestMapping("/welcome")
	public String welcome() {
		return service.getWelcomeMessage();
	}
}