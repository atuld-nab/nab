package au.com.anz.controller;

import static org.slf4j.LoggerFactory.getLogger;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import au.com.anz.service.TransactionService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TransactionController {

	private TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	Logger logger = getLogger(TransactionController.class);

	@GetMapping(path = "/getAll", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BasicAccountResponse> getProfit() {
		BasicAccountResponse response= transactionService.findAll();
		if(null!=response)
		logger.info("getting  controller for all accountBasic transaction" + response.getResponse().size());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping(path = "/getDetail/{code}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BasicAccountResponse> getDataByCode(@PathVariable("code") @NotNull String code) {
		logger.info("getting controller data for account" + code);
		BasicAccountResponse response = transactionService.findByAccountNumber(code);
		if (null != response && response.getResponse() != null && response.getResponse().size() > 0)
			logger.info("getting  controller for single account" + response.getResponse().size());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	
}
