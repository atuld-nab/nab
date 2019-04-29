package au.com.nab.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import au.com.nab.model.ProfitResponseModel;
import au.com.nab.service.CurrencyService;

@RestController
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;
    Logger logger = getLogger(CurrencyController.class);

    @GetMapping(path ="/getAll",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ProfitResponseModel>>getProfit()
    {
        logger.info("getting  controller for all codes");
        return new ResponseEntity<>(currencyService.computeDailyProfit(),HttpStatus.OK);

    }

    @GetMapping(path ="/getCode/{code}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProfitResponseModel>getDataByCode(@PathVariable("code")@NotNull String code)
    {
        logger.info("getting controller data for code"+code);

        return new ResponseEntity<>(currencyService.computeDailyProfitByCode(code),HttpStatus.OK);

    }
}
