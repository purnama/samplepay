package com.cgi.soa.masterclass.samplepay.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cgi.soa.masterclass.samplepay.validator.AccountOwnerValidator;

@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountOwnerValidator.class)
@Documented
public @interface AccountOwner {

	String message() default "{error.validator.account.owner}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	String accountNumber();
	
	String accountFirstName();
	
	String accountLastName();
}
