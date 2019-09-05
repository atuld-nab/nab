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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import au.com.anz.model.Contact;
import au.com.anz.service.ContactService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ContactController {

	private ContactService contactService;

	@Autowired
	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}

	Logger logger = getLogger(ContactController.class);

	@GetMapping(path = "/compare/{list1}/{list2}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BasicContactResponse> compare(@PathVariable(name = "list1") String list1,@PathVariable(name = "list2") String list2) {
		BasicContactResponse response= contactService.compareAddressBook(list1,list2);
		if(null!=response)
		logger.info("getting  controller for all addressbook comarison" + response.getResponse().size());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping(path = "/getDetail/{code}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BasicContactResponse> getDataByAddressBook(@PathVariable("code") @NotNull String code) {
		logger.info("getting controller data for addressbook" + code);
		BasicContactResponse response = contactService.findByAddressBook(code);
		if (null != response && response.getResponse() != null && response.getResponse().size() > 0)
			logger.info("getting controller data for addressbook" + response.getResponse().size());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	@PostMapping(value = "/save",consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	  public ResponseEntity<String> persistPerson(@RequestParam("address") String address ,@RequestBody Contact person) {
		logger.info("getting controller persisting data for addressbook" + person.toString());
		contactService.appendContact(address, person);
		logger.info("publishing done to  csv file"+person.toString());
	      return ResponseEntity.status(HttpStatus.CREATED).build();
	    
	    
	  }
	
}
