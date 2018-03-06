package com.popa.springboot;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.popa.beans.CubsTeam;
import com.popa.beans.Team;
import com.popa.service.WelcomeService;

//(the spring will scan the restcontroller classes, will instance the WelcomeController class and will see that
// it need the WelcomeService class and will create the bean and will autowire in there, after that will launch the
// application
@RestController
public class WelcomeController {
	
	//Dependencies (spring creates the beans and inject them where are needed
	@Autowired
	private WelcomeService service;
	
	//Autowired - by type -  Qualifier - identify bean by name - when are more then one bean of the same type
	@Autowired @Qualifier("blueSox")
	private Team blueSox;
	//@Resource - autowired by name, search for the bean with the name of the attribute
	@Resource
	private Team redSox;
	
	@Autowired
	private CubsTeam cubsTeam;
	
	@RequestMapping("/welcome")
	public String welcome() {
		return service.getWelcomeMessage() + " " + redSox.getName() + 
			" versus " + cubsTeam.getName() + " after team " + blueSox.getName() + " is sent home." ;
	}
}