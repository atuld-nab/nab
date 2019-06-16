package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import au.com.nab.Exception.OperatorServiceException;
import au.com.nab.controller.ContactResponse;
import au.com.nab.model.Contact;
import au.com.nab.model.Customer;
import au.com.nab.repository.CustomerRepository;
import au.com.nab.service.OperatorServiceImpl;

public class OperatorServiceTest {

	@InjectMocks
	private OperatorServiceImpl operatorService;

	@Mock
	private CustomerRepository repo;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	}

	@After
	public void cleanUp() {
		repo.deleteAll();
	}

	@Test
	public void getRepoTest() throws Exception {
		Customer cust1 = new Customer("1980", "pd");
		Contact contactPersonal = new Contact();
		contactPersonal.setCustomer(cust1);
		contactPersonal.setMsisdn("0410568600");
		Contact home = new Contact();
		home.setCustomer(cust1);
		home.setMsisdn("0410734700");
		cust1.addContacts(contactPersonal);
		cust1.addContacts(home);
		List<Customer> custList = new ArrayList<>();
		custList.add(cust1);
		when(repo.findAll()).thenReturn(custList);
		assertEquals(1, repo.findAll().size());
	}

	@Test
	public void getRepoByNameTest() throws Exception {
		Customer cust1 = new Customer("1980", "pd");
		Contact contactPersonal = new Contact();
		contactPersonal.setCustomer(cust1);
		contactPersonal.setMsisdn("0415514100");
		Contact home = new Contact();
		home.setCustomer(cust1);
		home.setMsisdn("0410734700");
		cust1.addContacts(contactPersonal);
		cust1.addContacts(home);
		when(operatorService.findByCustName("pd")).thenReturn(cust1);
		assertEquals(2, operatorService.findByCustName("pd").getContacts().size());
	}

	@Test(expected = OperatorServiceException.class)
	public void testOperatorServiceException() throws OperatorServiceException {
		Customer cust1 = new Customer("1980", "pd");
		Contact contactPersonal = new Contact();
		contactPersonal.setCustomer(cust1);
		contactPersonal.setMsisdn("0415514100");
		Contact home = new Contact();
		home.setCustomer(cust1);
		home.setMsisdn("0410734700");
		cust1.addContacts(contactPersonal);
		cust1.addContacts(home);
		operatorService.enableContact("0410568600");
	}
}
