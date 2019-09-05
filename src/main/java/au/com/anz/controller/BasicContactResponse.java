package au.com.anz.controller;

import java.util.ArrayList;
import java.util.List;

import au.com.anz.model.Contact;

public class BasicContactResponse {

public List<Contact> getResponse() {
		return response;
	}

	public void setResponse(List<Contact> response) {
		this.response = response;
	}

private List<Contact>response= new ArrayList<>();
}
