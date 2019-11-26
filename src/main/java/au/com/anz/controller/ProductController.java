package au.com.anz.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;
import java.util.Map;

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

import au.com.anz.model.Product;
import au.com.anz.service.ProductService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ProductController {

	private ProductService productService;

	@Autowired
	public ProductController(ProductService contactService) {
		this.productService = contactService;
	}

	Logger logger = getLogger(ProductController.class);

	@GetMapping(path = "/getDetail/{code}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Product> getDataBySkuCode(@PathVariable("code") @NotNull Long code) {
		logger.info("getting controller data for Product for sku code" + code);
		Product response = productService.findBySkuCode(code);
		if (null != response)
			logger.info("getting controller data for product" + response.getSku());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping(path = "/getPriceGrouping", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getPriceGrouping() {
		ResponseEntity<?> result = null;
		logger.info("getting controller data for Product getPriceGrouping");
		Map<Double, List<Product>> response = productService.groupByPrice();
		if (null != response && (!response.isEmpty())) {

			result = new ResponseEntity<>(response, HttpStatus.OK);

		}
		return result;
	}

	@GetMapping(path = "/getColorGrouping", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getColorGrouping() {
		ResponseEntity<?> result = null;
		logger.info("getting controller data for Product getColorGrouping");
		Map<String, List<Product>> response = productService.groupByColor();
		if (null != response && (!response.isEmpty())) {

			result = new ResponseEntity<>(response, HttpStatus.OK);

		}
		return result;
	}

	@GetMapping(path = "/getBrandGrouping", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getBrandGrouping() {
		ResponseEntity<?> result = null;
		logger.info("getting controller data for Product getBrandGrouping");
		Map<String, List<Product>> response = productService.groupByBrand();
		if (null != response && (!response.isEmpty())) {

			result = new ResponseEntity<>(response, HttpStatus.OK);

		}
		return result;
	}

	@GetMapping(path = "/getSizeGrouping", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getSizeGrouping() {
		ResponseEntity<?> result = null;
		logger.info("getting controller data for Product getSizeGrouping");
		Map<String, List<Product>> response = productService.groupBySize();
		if (null != response && (!response.isEmpty())) {

			result = new ResponseEntity<>(response, HttpStatus.OK);

		}
		return result;
	}

	@GetMapping(path = "/getSupplierProductCount", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getSupplierProductCount() {
		ResponseEntity<?> result = null;
		logger.info("getting controller data for Product getSupplierProductCount");
		Map<String, Long> response = productService.supplierWiseProductCount();
		if (null != response && (!response.isEmpty())) {

			result = new ResponseEntity<>(response, HttpStatus.OK);

		}
		return result;
	}
}
