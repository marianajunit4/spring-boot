package com.popa.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.popa.entities.Account;
import com.popa.repositories.AccountRepository;
import com.popa.service.AccountService;

@RestController
public class DBAccessController {
	
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountService service;

	@RequestMapping("/dbshow")
	public String dbshow() {
		List<Account> accounts = accountRepository.getAccounts();
		StringBuilder textBuilder = new StringBuilder();
		for (Account account : accounts) {
			textBuilder.append(" Account " + account.getId() + " with balance "+ account.getBalance() +"; ");
		}
		
		if(accounts.size() > 0) {
			long id = accounts.get(0).getId();
			textBuilder.append("Balance of the account " + id + " is " + service.getBalance(id));
		}
		return textBuilder.toString();
	}
}