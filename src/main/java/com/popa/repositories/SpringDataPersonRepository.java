package com.popa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.popa.jpa.entities.Person;

//JPARepository ---> data type of the repository and Long from the data type of the primary key
//can sort, default methods of CRUD, all the methods are already implemented
public interface SpringDataPersonRepository extends JpaRepository<Person, Long> {
	
	List<Person> findPersonsByAgeGreaterThanEqual(Integer age);
}
