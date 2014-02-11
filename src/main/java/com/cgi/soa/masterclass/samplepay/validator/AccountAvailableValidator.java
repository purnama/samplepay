package com.cgi.soa.masterclass.samplepay.validator;

import javax.enterprise.context.RequestScoped;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cgi.soa.masterclass.samplepay.service.BankWs;
import com.cgi.soa.masterclass.samplepay.validator.annotation.AccountAvailable;
import com.cgi.soa.masterclass.samplepay.validator.factory.InjectingConstraintValidatorFactory;

@RequestScoped
public class AccountAvailableValidator implements ConstraintValidator<AccountAvailable, Integer> {
	

	BankWs bankWs;

	@Override
	public void initialize(AccountAvailable arg0) {
		bankWs = InjectingConstraintValidatorFactory.getInstance(BankWs.class);
	}

	@Override
	public boolean isValid(Integer accountId, ConstraintValidatorContext context) {
			return bankWs.getService().isAccountExist(accountId);
	}

}
