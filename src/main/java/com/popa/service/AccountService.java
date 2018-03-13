package com.popa.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.popa.entities.Account;
import com.popa.repositories.AccountRepository;

@Service
@Transactional//ALL THE METHODS RUNS IN TRANSACTION
@Profile("test")
public class AccountService {
	
	@Autowired
	private AccountRepository repository;
	
	//go ahead and read this outside of a transaction, will not affect the db
	@Transactional (readOnly = true, propagation = Propagation.SUPPORTS) // ---> this annotation works like a AOP, with @Around method, will create a transaction or not depending on the propagation type
	public BigDecimal getBalance(Long id) {
		return repository.getAccount(id).getBalance();
	}
	
	public BigDecimal deposit(Long id, BigDecimal amount) {
		Account account = repository.getAccount(id);
		BigDecimal newBalance = account.getBalance().add(amount);
		account.setBalance(newBalance);
		repository.updateAccount(account);
		return newBalance;
	}
	
	public BigDecimal withDraw(Long id, BigDecimal amount) {
		return deposit(id, amount.negate());
	}
	
	public void transfer(Long fromId, Long toId, BigDecimal amount) {
		withDraw(fromId, amount);
		deposit(toId, amount);
	}
	
}
