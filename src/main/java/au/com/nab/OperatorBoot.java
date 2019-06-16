package au.com.nab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import au.com.nab.model.Contact;
import au.com.nab.model.Customer;
import au.com.nab.repository.CustomerRepository;


@org.springframework.boot.autoconfigure.SpringBootApplication
@EnableAutoConfiguration
public class OperatorBoot implements CommandLineRunner{
	
	 @Autowired
	 private CustomerRepository customerRepository;
	public static void main (String [] args)
	{
		SpringApplication.run(OperatorBoot.class,args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Customer cust1= new Customer("1980","pd");
		Contact contactPersonal = new Contact();
		contactPersonal.setMsisdn("0415514100");
		cust1.addContacts(contactPersonal);
		Contact home =  new Contact();
		home.setMsisdn("0410734700");
		cust1.addContacts(home);
		Customer customerParent = new Customer("1954","jas");
		Contact foriegnContact = new Contact();
		foriegnContact.setMsisdn("999955062");
		customerParent.addContacts(foriegnContact);
		Customer sibling=new Customer("1986","ad");
		Contact primeNumber=new Contact();
		primeNumber.setMsisdn("0410568600");
		sibling.addContacts(primeNumber);
		customerRepository.save(cust1);
		customerRepository.save(customerParent);
		customerRepository.save(sibling);
		
		
	}

}
