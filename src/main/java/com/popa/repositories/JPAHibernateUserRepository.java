package com.popa.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.popa.jpa.entities.User;


@Repository
@Profile({"prod", "test"})
public class JPAHibernateUserRepository implements UserRepository{
	
	private long nextId = 4;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public int getNumberOfUsers() {
        String jpaTxt = "select count(u.id) from User u";
        Long result = (Long) entityManager.createQuery(jpaTxt)
                .getSingleResult();
        return result.intValue();
    }

    @Override
    public Long createUser(String name) {
        long id = nextId++;
        entityManager.persist(new User(id, name));
        return id;
    }

    @Override
    public int deleteUser(Long id) {
        entityManager.remove(getUser(id));
        return 1;
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}
