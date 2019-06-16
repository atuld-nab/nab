package au.com.nab.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import au.com.nab.OperatorBoot;
import au.com.nab.model.Contact;
import au.com.nab.model.Customer;
import au.com.nab.service.OperatorService;
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OperatorBoot.class)
@ContextConfiguration(classes = MockServletContext.class)
@WebMvcTest(OperatorController.class)
public class OperatorControllerTest {

	private MockMvc mvc;
	private OperatorService operatorService;

    
    @Before
    public void setUp()
     {
    	CustomerResponse response = new CustomerResponse();
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
		response.setResponse(custList);
    	operatorService =mock(OperatorService.class);
    	given(operatorService.findAll()).willReturn(response);
    	mvc = MockMvcBuilders.standaloneSetup(new OperatorController(operatorService)).build();

    	}

    @Test
    public void canFetchAll() throws Exception {
        this.mvc.perform(get("/getAll")).andExpect(status().isOk());

    }


    @Test
    public void canGetAll() throws Exception
    {
        this.mvc.perform(get("/getCustomer/{code}","pd")).andExpect(status().isOk());
    }

    @Test
    public void getCustomerByName()throws Exception {
   MvcResult result= mvc.perform(get("/getCustomer/{code}","pd").
    		accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
   	Assert.assertNotNull(result.getResponse().getContentAsString());
    }
}