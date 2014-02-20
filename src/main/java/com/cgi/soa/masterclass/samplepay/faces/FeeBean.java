package com.cgi.soa.masterclass.samplepay.faces;

import java.text.DecimalFormat;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cgi.soa.masterclass.samplepay.model.Fee;
import com.cgi.soa.masterclass.samplepay.service.Repository;

@Named
@RequestScoped
public class FeeBean {

	@Inject
	Repository repository;
	
	
	public List<Fee> getFees(){
		return repository.getFees();
	}
	
	public String getBalance(){
		DecimalFormat format = new DecimalFormat( "###,###,###,##0.00" );
		return format.format(repository.getFeeBalance());
	}
	
}
