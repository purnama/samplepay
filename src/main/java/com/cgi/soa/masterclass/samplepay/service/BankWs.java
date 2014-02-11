package com.cgi.soa.masterclass.samplepay.service;

import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;

import com.cgi.soa.masterclass.samplepay.samplebank.ws.BankWeb;
import com.cgi.soa.masterclass.samplepay.samplebank.ws.BankWebService;

@Stateless
public class BankWs {

	@WebServiceRef
	BankWebService webService;
	
	public BankWeb getService(){
		return webService.getBankWebPort();
	}
}
