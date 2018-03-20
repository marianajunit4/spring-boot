package com.popa;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.popa.config.AppConfig;
import com.popa.jpa.entities.Person;
import com.popa.repositories.SpringDataPersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles(profiles = "test")
@Transactional
public class PersonRepositoryTest {

    @Autowired
    private SpringDataPersonRepository repository;
 
    @Test
    public void testGet() throws Exception {
        List<Person> persons = repository.findAll();
        assertThat(persons.size(), is(2));
    }

    @Test
    public void testGetPerson() throws Exception {
        Person person = repository.findOne(1L);
        assertThat(person.getId(), is(1L));
        assertThat(31, is(person.getAge()));
    }

    @Test
    public void testGetNumberOf() throws Exception {
        assertThat(repository.count(), is(2L));
    }

    @Test
    public void testCreatePerson() throws Exception {
        Person person = new Person(99L, "Mariana", 31);
        repository.save(person);
        Long id = person.getId();
        assertThat(id, is(notNullValue()));

        Person again = repository.findOne(id);
        assertThat(again.getId(), is(id));
        assertThat(again.getAge(), is(31));
    }

    @Test
    public void testUpdatePerson() throws Exception {
        Person person = repository.findOne(1L);
        Integer current = person.getAge();
        person.setAge(36);
        repository.save(person);

        Person again = repository.findOne(1L);
        assertThat(again.getAge(), is(36));
    }

    @Test
    public void testDeletePerson() throws Exception {
        repository.deleteAll();
        assertThat(repository.count(), is(0L));
    }

    @Test
    public void testAgeGTE() throws Exception {
        List<Person> persons = repository.findPersonsByAgeGreaterThanEqual(32);
        assertThat(persons.size(), is(1));
    }
}