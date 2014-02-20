package com.cgi.soa.masterclass.samplepay.faces;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cgi.soa.masterclass.samplepay.model.Transaction;
import com.cgi.soa.masterclass.samplepay.model.User;
import com.cgi.soa.masterclass.samplepay.service.Repository;

@Named
@RequestScoped
public class TransactionBean {

	@Inject
	Repository repository;
	
	User user;
	
	Transaction transaction;
	
	
	public String pay(){
		User user = repository.pay(transaction);
		return "/transactions/index.xhtml?user="+user.getId()+"&faces-redirect=true";
	}
	
	public String deposit(){
		transaction.setRecipient(transaction.getUser());
		User user = repository.deposit(transaction);
		return "/transactions/index.xhtml?user="+user.getId()+"&faces-redirect=true";
	}
	
	public String clearing(){
		transaction.setRecipient(transaction.getUser());
		User user = repository.clearing(transaction);
		return "/transactions/index.xhtml?user="+user.getId()+"&faces-redirect=true";
	}
	
	public List<User> getUsers(){
		return repository.getUsers();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Transaction getTransaction() {
		if(transaction == null){
			transaction = new Transaction();
		}
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	
}
