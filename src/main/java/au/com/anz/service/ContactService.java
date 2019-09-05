package au.com.anz.service;

import au.com.anz.Exception.ContactServiceException;
import au.com.anz.controller.BasicContactResponse;
import au.com.anz.model.Contact;

public interface ContactService {
    public BasicContactResponse compareAddressBook(final String addressBook1,final String addressBook2) throws ContactServiceException;
    public BasicContactResponse findByAddressBook(final String adressBook) throws ContactServiceException;
    public void appendContact(final String addressBook, final Contact data) throws ContactServiceException;
   }
