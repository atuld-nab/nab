package au.com.anz;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import au.com.anz.model.AccountTransaction;
import au.com.anz.model.BasicAccountEnquiry;
import au.com.anz.repository.BasicAccountEnquiryRepository;


@org.springframework.boot.autoconfigure.SpringBootApplication
@EnableAutoConfiguration
public class OperatorBoot implements CommandLineRunner{
	
	 @Autowired
	 private BasicAccountEnquiryRepository repository;
	public static void main (String [] args)
	{
		SpringApplication.run(OperatorBoot.class,args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy",Locale.ENGLISH);
		DateTimeFormatter basicFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		
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
		
		/************/
		
		
		
		BasicAccountEnquiry acc2= new BasicAccountEnquiry();
		
		acc2.setAccountName("AUDSavings726");
		acc2.setType("CREDIT");
		acc2.setCurrency("SGD");
		acc2.setAccountNumber("12233344");
		acc2.setOpeningBalance(60000.00);
		LocalDate dateAccount2 = LocalDate.of(2014, Month.JUNE, 20);
		String accountDate = dateAccount2.format(basicFormatter);
		acc2.setBalanceDate(accountDate);
		
		List<AccountTransaction>usdList= new ArrayList<>();
		acc2.setTransactions(usdList);
		
		
		
		AccountTransaction tr3= new AccountTransaction();
		tr3.setAccountName("Credit Account");
		tr3.setAccountNumber("12233344");
		tr3.setCurrency("USD");
		tr3.setTransactionType("Credit");
		tr3.setCredit(1000.00);
		LocalDate date3 = LocalDate.of(2014, Month.APRIL, 20);
		String date=date3.format(formatter);
		tr3.setValueDate(date);
		
		
		
		
		AccountTransaction tr4= new AccountTransaction();
		tr4.setAccountName("Savings Account");
		tr4.setAccountNumber("12233344");
		tr4.setCurrency("USD");
		tr4.setTransactionType("Credit");
		tr4.setCredit(1000.00);
		LocalDate date4 = LocalDate.of(2014, Month.FEBRUARY, 20);
		String dateString =date4.format(formatter);
		tr4.setValueDate(dateString);
		acc2.addTransaction(tr3);
		acc2.addTransaction(tr4);
		
		repository.save(acc1);
		repository.save(acc2);
		
		
	}

}
