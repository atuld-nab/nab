package au.com.nab.controller;

import java.util.List;

import au.com.nab.model.Customer;

public class CustomerResponse {
public List<Customer> getResponse() {
		return response;
	}

	public void setResponse(List<Customer> response) {
		this.response = response;
	}

private List<Customer>response;
}
