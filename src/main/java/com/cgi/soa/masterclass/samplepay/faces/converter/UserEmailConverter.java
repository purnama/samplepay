package com.cgi.soa.masterclass.samplepay.faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.cgi.soa.masterclass.samplepay.model.User;
import com.cgi.soa.masterclass.samplepay.service.Repository;

@FacesConverter(value="userEmailConverter")
public class UserEmailConverter implements Converter {

	@Inject
	Repository repository;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return repository.findByEmail(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((User) value).getId().toString();
	}

}
