package service;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import au.com.anz.model.AccountTransaction;
import au.com.anz.model.BasicAccountEnquiry;
import au.com.anz.repository.BasicAccountEnquiryRepository;
import au.com.anz.service.TransactionServiceImpl;

public class TransactionServiceTest {
	@InjectMocks
	private TransactionServiceImpl transactionService;
	
	
	DateTimeFormatter formatter ;
	DateTimeFormatter basicFormatter;
	
	@Mock
	private BasicAccountEnquiryRepository repo;
	

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy",Locale.ENGLISH);
		basicFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		

	}
	@Test
	public void getRepoTest() throws Exception {
		
		
		BasicAccountEnquiry acc1= new BasicAccountEnquiry();
		
		
		acc1.setAccountName("SGDSavings726");
		acc1.setType("CREDIT");
		acc1.setCurrency("SGD");
		acc1.setOpeningBalance(85000.00);
		acc1.setAccountNumber("12133344");
		LocalDate dateAccount = LocalDate.of(2014, Month.AUGUST, 20);
		String dateTransaction=dateAccount.format(basicFormatter);
		acc1.setBalanceDate(dateTransaction);
		
		AccountTransaction tr1= new AccountTransaction();
		tr1.setAccountName("Current Account");
		LocalDate dateTime = LocalDate.of(2012, Month.SEPTEMBER, 20);
		String dateTransactionString=dateTime.format(formatter);
		tr1.setAccountNumber("12133344");
		tr1.setCurrency("SGD");
		tr1.setTransactionType("Credit");
		tr1.setCredit(1000.00);
		tr1.setValueDate(dateTransactionString);
		
		AccountTransaction tr2= new AccountTransaction();
		tr2.setAccountName("OverDraft Account");
		tr2.setAccountNumber("12133344");
		tr2.setCurrency("AUD");
		tr2.setTransactionType("Credit");
		tr2.setCredit(1000.00);
		LocalDate date2 = LocalDate.of(2014, Month.MAY, 20);
		String datedString=date2.format(formatter);
		tr2.setValueDate(datedString);
		
		List<AccountTransaction>sgdList= new ArrayList<>();
		acc1.setTransactions(sgdList);
		acc1.addTransaction(tr2);
		acc1.addTransaction(tr1);
		
		List<BasicAccountEnquiry>accountList= new ArrayList<>();
		
		accountList.add(acc1);
		when(repo.findAll()).thenReturn(accountList);
		assertEquals(1, repo.findAll().size());
	}
	}
