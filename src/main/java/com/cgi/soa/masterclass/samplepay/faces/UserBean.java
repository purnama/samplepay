package com.cgi.soa.masterclass.samplepay.faces;

import java.rmi.RemoteException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceRef;

import com.cgi.soa.masterclass.samplepay.model.User;
import com.cgi.soa.masterclass.samplepay.samplebank.ws.BankWeb;
import com.cgi.soa.masterclass.samplepay.samplebank.ws.BankWebService;
import com.cgi.soa.masterclass.samplepay.service.Repository;

@Named
@RequestScoped
public class UserBean {

	@Inject
	Repository repository;
	
	@WebServiceRef(wsdlLocation="http://localhost:8080/samplebank/BankWebService?wsdl")
	BankWebService webService;
	
	User user;
	
	public String create(){
		repository.createUser(user);
		return "/users/index.xhtml?faces-redirect=true";
	}
	
	public String getText() throws RemoteException{
		return Boolean.toString(webService.getBankWebPort().isAccountExist(5));
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
