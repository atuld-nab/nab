package service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import au.com.anz.controller.BasicContactResponse;
import au.com.anz.model.Contact;
import au.com.anz.service.ContactServiceImpl;

public class ContactServiceTest {
	@InjectMocks
	private ContactServiceImpl contactService;
	

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void getcompareAddressBook() throws Exception {
		Contact tr1= new Contact("atulk","0410734700");
		Contact cont = new Contact("payal","0410734700");
		List<Contact>arrayList= new ArrayList<Contact>();
		arrayList.add(tr1);
		arrayList.add(cont);
		BasicContactResponse response = new BasicContactResponse();
		response.setResponse(arrayList);
		when(contactService.compareAddressBook("north","south")).thenReturn(response);
		assertEquals(2, contactService.compareAddressBook("north","south").getResponse().size());
	}
	
	
	@Test
	public void testFindByAddressBook()throws Exception
	{
		Contact cont = new Contact("golu","9953290636");
		List<Contact>arrayList= new ArrayList<Contact>();
		arrayList.add(cont);
		BasicContactResponse response = new BasicContactResponse();
		response.setResponse(arrayList);
		when(contactService.findByAddressBook("north")).thenReturn(response);
		assertEquals(1, contactService.findByAddressBook("north").getResponse().size());
	}
	
	}
