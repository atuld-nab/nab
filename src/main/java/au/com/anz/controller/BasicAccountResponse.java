package au.com.anz.controller;

import java.util.ArrayList;
import java.util.List;

import au.com.anz.model.BasicAccountEnquiry;

public class BasicAccountResponse {

public List<BasicAccountEnquiry> getResponse() {
		return response;
	}

	public void setResponse(List<BasicAccountEnquiry> response) {
		this.response = response;
	}

private List<BasicAccountEnquiry>response= new ArrayList<>();
}
