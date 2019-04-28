package au.com.nab;
import au.com.nab.controller.CurrencyController;
import au.com.nab.model.Currency;
import au.com.nab.repository.CurrencyRepository;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration


public class LoadDataBase {
    Logger log = getLogger(LoadDataBase.class);
    @Bean
    CommandLineRunner initDatabase(CurrencyRepository repository){
        return args -> {
            log.info("preloading "+repository.save(new Currency("BTC",34.90,LocalDateTime.parse("2018-05-07T09:15:00"))));
            log.info("preloading "+repository.save(new Currency("BTC",36.13,LocalDateTime.parse("2018-05-07T10:45:00"))));
            log.info("preloading "+repository.save(new Currency("BTC",37.01,LocalDateTime.parse("2018-05-07T12:30:00"))));
            log.info("preloading "+repository.save(new Currency("BTC",35.98,LocalDateTime.parse("2018-05-07T14:00:00"))));
            log.info("preloading "+repository.save(new Currency("BTC",33.56,LocalDateTime.parse("2018-05-07T15:30:00"))));
            log.info("preloading "+repository.save(new Currency("ETC",1.45,LocalDateTime.parse("2018-05-07T09:00:00"))));
            log.info("preloading "+repository.save(new Currency("ETC",1.87,LocalDateTime.parse("2018-05-07T10:30:00"))));
            log.info("preloading "+repository.save(new Currency("ETC",1.55,LocalDateTime.parse("2018-05-07T12:45:00"))));
            log.info("preloading "+repository.save(new Currency("ETC",2.01,LocalDateTime.parse("2018-05-07T15:15:00"))));
            log.info("preloading "+repository.save(new Currency("ETC",2.15,LocalDateTime.parse("2018-05-07T17:00:00"))));
            log.info("preloading "+repository.save(new Currency("LTC",14.32,LocalDateTime.parse("2018-05-07T09:30:00"))));
            log.info("preloading "+repository.save(new Currency("LTC",14.87,LocalDateTime.parse("2018-05-07T11:15:00"))));
            log.info("preloading "+repository.save(new Currency("LTC",15.03,LocalDateTime.parse("2018-05-07T12:45:00"))));
            log.info("preloading "+repository.save(new Currency("LTC",14.76,LocalDateTime.parse("2018-05-07T14:00:00"))));
            log.info("preloading "+repository.save(new Currency("LTC",14.15,LocalDateTime.parse("2018-05-07T17:00:00"))));





        };

    }
}
