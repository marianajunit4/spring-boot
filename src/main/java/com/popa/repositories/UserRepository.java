package com.popa.repositories;

import java.util.List;

import com.popa.jpa.entities.User;

public interface UserRepository {
	List<User> getUsers();
	User getUser(Long id);
	int getNumberOfUsers();
    Long createUser(String name);
    int deleteUser(Long id);
    void updateUser(User user);
	
}
