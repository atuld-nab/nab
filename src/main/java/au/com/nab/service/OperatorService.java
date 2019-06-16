package au.com.nab.service;

import au.com.nab.Exception.OperatorServiceException;
import au.com.nab.controller.ContactResponse;
import au.com.nab.controller.CustomerResponse;
import au.com.nab.model.Customer;

public interface OperatorService {
    public CustomerResponse findAll() throws OperatorServiceException;
    public Customer findByCustName(final String custName) throws OperatorServiceException;
    public ContactResponse enableContact(final String msisdn) throws OperatorServiceException;
    }
