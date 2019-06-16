package au.com.nab.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.nab.model.Contact;
import au.com.nab.model.Customer;
import au.com.nab.service.OperatorService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class OperatorController {

	private OperatorService operatorService;

	@Autowired
	public OperatorController(OperatorService operatorService) {
		this.operatorService = operatorService;
	}

	Logger logger = getLogger(OperatorController.class);

	@GetMapping(path = "/getAll", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CustomerResponse> getProfit() {
		CustomerResponse response= operatorService.findAll();
		if(null!=response)
		logger.info("getting  controller for all customer" + response.getResponse().size());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping(path = "/getCustomer/{code}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Customer> getDataByCode(@PathVariable("code") @NotNull String code) {
		logger.info("getting controller data for customer" + code);
		Customer customer = operatorService.findByCustName(code);
		if (null != customer && customer.getContacts() != null && customer.getContacts().size() > 0)
			logger.info("getting  controller for single customer" + customer.getContacts().size());
		return new ResponseEntity<>(customer, HttpStatus.OK);

	}

	@GetMapping(path = "/enableContact/{code}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ContactResponse> enableContact(@PathVariable("code") @NotNull String code) {
		logger.info("getting controller data for contact enable" + code);
		ContactResponse response = operatorService.enableContact(code);
		if (null != response) {
			logger.info("getting  controller for contact enable" + response.getContacts().size());

		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
