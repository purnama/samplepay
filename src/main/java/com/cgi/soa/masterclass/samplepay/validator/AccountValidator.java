package com.cgi.soa.masterclass.samplepay.validator;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;

import com.cgi.soa.masterclass.samplepay.validator.annotation.AccountAvailable;


public class AccountValidator implements ConstraintValidator<AccountAvailable, Integer> {
	

	
	@Inject
	Logger log;

	@Override
	public void initialize(AccountAvailable arg0) {
		// nothing to do
	}

	@Override
	public boolean isValid(Integer accountId, ConstraintValidatorContext context) {

			//return webService.isAccountExist(accountId);
			return false;
	}

}
