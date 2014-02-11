package com.cgi.soa.masterclass.samplepay.validator;

import java.lang.reflect.InvocationTargetException;

import javax.enterprise.context.RequestScoped;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

import com.cgi.soa.masterclass.samplepay.service.BankWs;
import com.cgi.soa.masterclass.samplepay.validator.annotation.AccountOwner;
import com.cgi.soa.masterclass.samplepay.validator.factory.InjectingConstraintValidatorFactory;

@RequestScoped
public class AccountOwnerValidator implements
		ConstraintValidator<AccountOwner, Object> {

	BankWs bankWs;

	String accountNumber;

	String accountFirstName;

	String accountLastName;

	@Override
	public void initialize(AccountOwner accountOwner) {
		bankWs = InjectingConstraintValidatorFactory.getInstance(BankWs.class);
		accountNumber = accountOwner.accountNumber();
		accountFirstName = accountOwner.accountFirstName();
		accountLastName = accountOwner.accountLastName();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {

		try {
			return bankWs.getService().isAccountOwner(
					Integer.valueOf(BeanUtils.getProperty(object, this.accountNumber)),
					BeanUtils.getProperty(object, this.accountFirstName),
					BeanUtils.getProperty(object, this.accountLastName));
		} catch (NumberFormatException | IllegalAccessException
				| InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

}
