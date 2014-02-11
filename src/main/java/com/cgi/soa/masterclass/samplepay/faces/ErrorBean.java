package com.cgi.soa.masterclass.samplepay.faces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class ErrorBean {

	public List<FacesMessage> getMessages() {
	    List<FacesMessage> messages = new ArrayList<FacesMessage>();
	    Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();
	    while (iter.hasNext()) {
	        messages.add(iter.next());
	    }
	    return messages;
	}
}
