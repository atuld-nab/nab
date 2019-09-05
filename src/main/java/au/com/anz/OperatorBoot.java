package au.com.anz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


@org.springframework.boot.autoconfigure.SpringBootApplication
@EnableAutoConfiguration
public class OperatorBoot {

	public static void main (String [] args)
	{
		SpringApplication.run(OperatorBoot.class,args);
	}

	
}
