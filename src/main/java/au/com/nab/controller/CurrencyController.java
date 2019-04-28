package au.com.nab.controller;

import au.com.nab.Exception.CurrencyServiceException;
import au.com.nab.model.Currency;
import au.com.nab.model.ProfitResponseModel;
import au.com.nab.service.CurrencyService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class CurrencyController {
    @Autowired
    CurrencyService serviceLayer;
    Logger logger = getLogger(CurrencyController.class);

    @GetMapping(path ="/getAll",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ProfitResponseModel>>getProfit()
    {
        logger.info("getting  controller for all codes");
        return new ResponseEntity<>(serviceLayer.computeDailyProfit(),HttpStatus.OK);

    }

    @GetMapping(path ="/getCode/{code}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProfitResponseModel>getDataByCode(@PathVariable("code")@NotNull String code)
    {
        logger.info("getting controller data for code"+code);

        return new ResponseEntity<>(serviceLayer.computeDailyProfitByCode(code),HttpStatus.OK);

    }
}
