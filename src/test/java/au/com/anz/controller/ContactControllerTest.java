package au.com.anz.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.mock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import au.com.anz.OperatorBoot;
import au.com.anz.model.Contact;
import au.com.anz.service.ContactService;
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OperatorBoot.class)
@ContextConfiguration(classes = MockServletContext.class)
@WebMvcTest(ContactController.class)
public class ContactControllerTest {

	private MockMvc mvc;
	private ContactService contactService;

    
    @Before
    public void setUp()
     {
    	contactService = mock(ContactService.class);
		mvc = MockMvcBuilders.standaloneSetup(new ContactController(contactService)).build();
    	     }

    @Test
    public void getDataByAddressBookStatus() throws Exception {
    	this.mvc.perform(get("/getDetail/{code}","south")).andExpect(status().isOk());

    }
  
    @Test
    public void getDataByAddressBookResponse()throws Exception {
    	MvcResult result= mvc.perform(get("/getDetail/{code}","south").
        		accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
       	Assert.assertNotNull(result.getResponse().getContentAsString());
   }
    
    
 @Test
 public void testComparisonEndPoint() throws Exception{
	 MvcResult mvcResult = mvc.perform(get("/compare/{list1}/{list2}", "north", "south"))
	            .andExpect(status().isOk()).andReturn();
		Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
 }
 @Test
 public void testsaveContact() throws Exception {
	 String jsonString = "{\n" +
             "\"name\":\"golu\",\n" +
             "\"phone\":\"9953290636\"\n" +
             "}";
	 mvc.perform(MockMvcRequestBuilders.post("/save?address=springvale")
             .contentType(MediaType.APPLICATION_JSON)
             .content(jsonString))
             .andExpect(MockMvcResultMatchers.status().isCreated());

 }
 }