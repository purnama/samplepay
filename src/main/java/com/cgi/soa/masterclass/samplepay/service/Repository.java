package com.cgi.soa.masterclass.samplepay.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cgi.soa.masterclass.samplepay.model.User;

@Stateless
public class Repository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<User> getUsers(){
		return (List<User>) entityManager.createQuery("SELECT user FROM "+User.class.getName()+" user ", User.class).getResultList();
	}
	
	public User findById(Integer userId){
		User user = entityManager.find(User.class, userId);
		return user;
	}
	
	public void createUser(User user){
		user.setBalance(BigDecimal.ZERO);
		entityManager.persist(user);
		entityManager.flush();
	}
	
	public User merge(User user){
		entityManager.merge(user);
		entityManager.flush();
		return entityManager.find(User.class, user.getId());
	}
}
