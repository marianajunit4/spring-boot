package com.popa.repositories;

import java.math.BigDecimal;
import java.util.List;

import com.popa.entities.Account;

//DAO (DATA ACCESS OBJECT)
public interface AccountRepository {
	List<Account> getAccounts();
	
	Account getAccount(Long id);
	
	int getNumberOfAccounts();
	
	Long createAccount(BigDecimal initialBalance);

	int deleteAccount(Long id);
	
	void updateAccount(Account accout);
}