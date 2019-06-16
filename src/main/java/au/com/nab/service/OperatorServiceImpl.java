package au.com.nab.service;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.nab.Exception.OperatorServiceException;
import au.com.nab.controller.ContactResponse;
import au.com.nab.controller.CustomerResponse;
import au.com.nab.model.Contact;
import au.com.nab.model.Customer;
import au.com.nab.repository.CustomerRepository;

@Service("operatorService")
public class OperatorServiceImpl implements OperatorService {

	@Autowired
	CustomerRepository repository;

	Logger log = getLogger(OperatorServiceImpl.class);

	@Override
	public CustomerResponse findAll() throws OperatorServiceException {
		List<Customer> response = null;
		CustomerResponse customerResponse = new CustomerResponse();
		try {

			response = repository.findAll();
			customerResponse.setResponse(response);
			if (null != response && response.size() > 0)
				log.info("fetching data for all customers with list size" + response.size());

		} catch (Exception ex) {
			log.error("error while fetching data for all customers" + ex.getMessage());
			throw new OperatorServiceException("error while fetching data for all customers" + ex.getMessage());
		}
		return customerResponse;
	}

	@Override
	public Customer findByCustName(String custName) throws OperatorServiceException {
		// TODO Auto-generated method stub
		Customer response = null;
		try {
			response = repository.findByName(custName);
			if (null != response)
				log.info("fetching data for all customers with name:-" + custName);

		} catch (Exception ex) {
			log.error("error while fetching data for single customers" + ex.getMessage());
			throw new OperatorServiceException("error while fetching data for single customers" + ex.getMessage());
		}
		return response;

	}

	@Override
	public ContactResponse enableContact(String msisdn) throws OperatorServiceException {
		// TODO Auto-generated method stub
		ContactResponse contactResponse = null;
		List<Contact> contactList = null;
		try {
			Customer response = repository.enableContact(msisdn);

			contactList = response.getContacts().stream().filter(d -> d.getMsisdn().equals(msisdn))
					.collect(Collectors.toList());
			if (contactList != null) {
				contactList.get(0).setEnable(true);
				contactResponse = new ContactResponse();
				repository.save(response);
				contactResponse.setContacts(contactList);
				log.info("enabling data for msisdn:-" + msisdn);
			}
		} catch (Exception ex) {
			log.error("error while enabling contact" + ex.getMessage());
			throw new OperatorServiceException("error while enabling contact" + ex.getMessage());
		}
		return contactResponse;

	}

}
