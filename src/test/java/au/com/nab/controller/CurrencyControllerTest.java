package au.com.nab.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import au.com.nab.CurrencyTrackerBoot;
import au.com.nab.model.PriceTimeStamp;
import au.com.nab.model.ProfitResponseModel;
import au.com.nab.service.CurrencyService;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import org.springframework.http.MediaType;
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CurrencyTrackerBoot.class)
@ContextConfiguration(classes = MockServletContext.class)
@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTest {

	private MockMvc mvc;
    private CurrencyService currencyService;
    ProfitResponseModel res ;

    
    @Before
    public void setUp()
    {
    	res=new ProfitResponseModel();
    	res = new ProfitResponseModel();
    	res.setBuyingPrice(new PriceTimeStamp(LocalTime.parse("09:15"),34.9));
    	res.setSellPrice(new PriceTimeStamp(LocalTime.parse("12:30"),37.01));
    	res.setCurrency("BTC");
    	res.setProfit(2.1099999999999994);
    	currencyService = mock(CurrencyService.class);
    	given(currencyService.computeDailyProfitByCode("BTC")).willReturn(res);
    	mvc = MockMvcBuilders.standaloneSetup(new CurrencyController(currencyService)).build();
    }

    @Test
    public void canFetchAll() throws Exception {
        this.mvc.perform(get("/getAll")).andExpect(status().isOk());

    }


    @Test
    public void canFetchByCurrencyCode() throws Exception
    {
        this.mvc.perform(get("/getCode/{code}","LTC")).andExpect(status().isOk());
    }

    @Test
    public void getCurrencyByCode()throws Exception {
   MvcResult result= mvc.perform(get("/getCode/{code}","BTC").
    		accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
   	Assert.assertNotNull(result.getResponse().getContentAsString());
   	Assert.assertTrue(result.getResponse().getContentAsString().contains("2.1099999999999994"));
   
    }
}