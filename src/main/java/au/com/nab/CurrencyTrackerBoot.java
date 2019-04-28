package au.com.nab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@org.springframework.boot.autoconfigure.SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = { "com.au.nab.model" })
public class CurrencyTrackerBoot {
	public static void main (String [] args)
	{
		SpringApplication.run(CurrencyTrackerBoot.class,args);
	}

}
