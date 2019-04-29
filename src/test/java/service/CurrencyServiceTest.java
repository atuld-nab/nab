package service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import au.com.nab.model.Currency;
import au.com.nab.model.PriceTimeStamp;
import au.com.nab.model.ProfitResponseModel;
import au.com.nab.repository.CurrencyRepository;
import au.com.nab.service.CurrencyServiceImpl;

public class CurrencyServiceTest {
	
	@InjectMocks
	private CurrencyServiceImpl service;
	
	@Mock
	private CurrencyRepository repo;
	ProfitResponseModel res = new ProfitResponseModel();

	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		res = new ProfitResponseModel();
    	res.setBuyingPrice(new PriceTimeStamp(LocalTime.parse("09:15"),34.9));
    	res.setSellPrice(new PriceTimeStamp(LocalTime.parse("12:30"),37.01));
    	res.setCurrency("BTC");
    	res.setProfit(2.1099999999999994);
    	
    }
	
	@After
	public void cleanUp()
	{
		repo.deleteAll();
	}
	
	@Test
	public void getServiceTestByCodeRepo() throws Exception
	{
		List<Currency>currencyList = new ArrayList<Currency>();
		currencyList.add(new Currency("BTC",34.90,LocalDateTime.parse("2018-05-07T09:15:00")));
		currencyList.add(new Currency("BTC",36.13,LocalDateTime.parse("2018-05-07T10:45:00")));
		currencyList.add(new Currency("BTC",37.01,LocalDateTime.parse("2018-05-07T12:30:00")));
		currencyList.add(new Currency("BTC",35.98,LocalDateTime.parse("2018-05-07T14:00:00")));
		currencyList.add((new Currency("BTC",33.56,LocalDateTime.parse("2018-05-07T15:30:00"))));
		when(repo.findAll()).thenReturn(currencyList);
		assertEquals(5, currencyList.size());
		ProfitResponseModel serviceResponse = service.computeDailyProfitByCode("BTC");
		assertEquals(serviceResponse.getCurrency(), res.getCurrency());
		assertEquals(serviceResponse.getProfit(), res.getProfit());
	}
}
