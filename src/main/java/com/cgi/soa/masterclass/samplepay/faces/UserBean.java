package com.cgi.soa.masterclass.samplepay.faces;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cgi.soa.masterclass.samplepay.model.User;
import com.cgi.soa.masterclass.samplepay.service.Repository;

@Named
@RequestScoped
public class UserBean {

	@Inject
	Repository repository;
	
	User user;
	
	
	public String create(){
		repository.createUser(user);
		return "/users/index.xhtml?faces-redirect=true";
	}
	
	public List<User> getUsers(){
		return repository.getUsers();
	}

	public User getUser() {
		if(user == null){
			user = new User();
		}
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
