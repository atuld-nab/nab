package au.com.nab.controller;

import java.util.List;

import au.com.nab.model.Contact;

public class ContactResponse {
public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

private List<Contact>contacts;
}
