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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import au.com.anz.OperatorBoot;
import au.com.anz.service.ProductService;
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OperatorBoot.class)
@ContextConfiguration(classes = MockServletContext.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	private MockMvc mvc;
	private ProductService contactService;

    
    @Before
    public void setUp()
     {
    	contactService = mock(ProductService.class);
		mvc = MockMvcBuilders.standaloneSetup(new ProductController(contactService)).build();
    	     }

    @Test
    public void getDataBySkuCode() throws Exception {
    	this.mvc.perform(get("/getDetail/{code}",1)).andExpect(status().isOk());

    }
  
    @Test
    public void testGroupingByPrice()throws Exception {
    	MvcResult result= mvc.perform(get("/getPriceGrouping").
        		accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
       	Assert.assertNotNull(result.getResponse().getContentAsString());
   }
    
    
 @Test
 public void testGroupingbyBrand() throws Exception{
	 MvcResult mvcResult = mvc.perform(get("/getBrandGrouping"))
	            .andExpect(status().isOk()).andReturn();
		Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
 }
 
 }