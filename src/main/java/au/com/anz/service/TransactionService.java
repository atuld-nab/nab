package au.com.anz.service;

import au.com.anz.Exception.TransactionServiceException;
import au.com.anz.controller.BasicAccountResponse;

public interface TransactionService {
    public BasicAccountResponse findAll() throws TransactionServiceException;
    public BasicAccountResponse findByAccountNumber(final String accountNumber) throws TransactionServiceException;
   }
