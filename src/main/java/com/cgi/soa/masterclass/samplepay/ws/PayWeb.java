package com.cgi.soa.masterclass.samplepay.ws;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.cgi.soa.masterclass.samplepay.model.Transaction;
import com.cgi.soa.masterclass.samplepay.model.User;
import com.cgi.soa.masterclass.samplepay.service.Repository;

@WebService
public class PayWeb {

	@Inject
	private Repository repository;
	
	@WebMethod
	public boolean isUserExist(String email){
		User user = repository.findByEmail(email);
		return user == null ? false : true;
	}
	
	@WebMethod
	public void pay(String senderEmail, String recipientEmail, String purpose, BigDecimal amount){
		Transaction transaction = new Transaction();
		transaction.setPurpose(purpose);
		transaction.setAmount(amount);
		User sender = repository.findByEmail(senderEmail) ;
		User recipient = repository.findByEmail(recipientEmail);
		transaction.setUser(sender);
		transaction.setRecipient(recipient);
		repository.pay(transaction);
	}
}
