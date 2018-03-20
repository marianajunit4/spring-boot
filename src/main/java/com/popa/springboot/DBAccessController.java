package com.popa.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.popa.entities.Account;
import com.popa.repositories.AccountRepository;
import com.popa.repositories.SpringDataPersonRepository;
import com.popa.repositories.UserRepository;
import com.popa.service.AccountService;

@RestController
public class DBAccessController {
	
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SpringDataPersonRepository personRepository;
	
	@Autowired
	private AccountService service;

	@RequestMapping("/dbshow")
	public String dbshow() {
		List<Account> accounts = accountRepository.getAccounts();
		StringBuilder textBuilder = new StringBuilder();
		for (Account account : accounts) {
			textBuilder.append("<br>  Account " + account.getId() + " with balance "+ account.getBalance() +"; ");
		}
		
		if(accounts.size() > 0) {
			long id = accounts.get(0).getId();
			textBuilder.append("<br>  Balance of the account " + id + " is " + service.getBalance(id));
		}
		
		textBuilder.append("<br>  Number of available User is " + userRepository.getNumberOfUsers());
		
		textBuilder.append("<br> Number of available Person is " + personRepository.count());
		
		return textBuilder.toString();
	}
}