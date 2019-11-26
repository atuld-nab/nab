package au.com.anz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.anz.Exception.ProductServiceException;
import au.com.anz.model.Product;
import au.com.anz.repository.BrandRepository;
import au.com.anz.repository.ProductRepository;
import au.com.anz.repository.SupplierRepository;

@Transactional(readOnly=true,rollbackFor= {ProductServiceException.class})
@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository repository;
	@Autowired
	SupplierRepository supplierRepository;
	@Autowired
	BrandRepository brandRepository;

	private static final Logger log = Logger.getLogger("ProductServiceImpl.class");

	@Override
	public Map<String, List<Product>> groupByColor() throws ProductServiceException {
		log.info("getting groupby color for product");
		Map<String, List<Product>> result = null;

		try {
			List<Product> productList = (List<Product>) repository.findAll();
			if (!productList.isEmpty()) {
				result = productList.stream().collect(Collectors.groupingBy(Product::getColor));
			}
		} catch (Exception ex) {
			throw new ProductServiceException("exception in groupByColor" + ex.getMessage());

		}

		return result;
	}

	@Override
	public Map<String, List<Product>> groupByBrand() throws ProductServiceException {
		log.info("getting groupby brand for product");
		Map<Long, List<Product>> result = null;
		Map<String, List<Product>> namedMap = new HashMap<String, List<Product>>();

		try {

			List<Product> productList = (List<Product>) repository.findAll();
			if (!productList.isEmpty()) {
				result = productList.stream().collect(Collectors.groupingBy(Product::getBrand_id));
				for (Long id : result.keySet()) {
					namedMap.put(brandRepository.findBrandNameById(id), result.get(id));
				}
			}
		} catch (Exception ex) {
			throw new ProductServiceException("exception in groupByBrand" + ex.getMessage());

		}

		return namedMap;
	}

	@Override
	public Map<String, List<Product>> groupBySize() throws ProductServiceException {
		log.info("getting groupby size for product");
		Map<String, List<Product>> result = null;

		try {
			List<Product> productList = (List<Product>) repository.findAll();
			if (!productList.isEmpty()) {
				result = productList.stream().collect(Collectors.groupingBy(Product::getSize));
			}

		} catch (Exception ex) {
			throw new ProductServiceException("exception in groupBySize" + ex.getMessage());

		}

		return result;
	}

	@Override
	public Product findBySkuCode(final Long skuCode) throws ProductServiceException {
		Product entity = null;
		log.info("getting productby  skucode for product" + skuCode);
		try {
			entity = repository.findOne(skuCode);
		} catch (Exception ex) {
			throw new ProductServiceException("exception in getting productby  skucode for product" + ex.getMessage());

		}
		return entity;
	}

	@Override
	public Map<Double, List<Product>> groupByPrice() throws ProductServiceException {
		log.info("getting groupByPrice for product");
		Map<Double, List<Product>> result = null;
		try {
			List<Product> productList = (List<Product>) repository.findAll();
			if (!productList.isEmpty()) {
				result = productList.stream().collect(Collectors.groupingBy(Product::getPrice));
			}

		} catch (Exception ex) {
			throw new ProductServiceException("exception in groupByPrice" + ex.getMessage());

		}

		return result;
	}

	@Override
	public Map<String, Long> supplierWiseProductCount() throws ProductServiceException {
		log.info("getting supplierWiseProductCount for product");
		Map<Long, Long> counting = null;
		Map<String, Long> namedMap = new HashMap<String, Long>();
		try {
			List<Product> productList = (List<Product>) repository.findAll();
			if (!productList.isEmpty()) {
				counting = productList.stream()
						.collect(Collectors.groupingBy(Product::getSupplier_id, Collectors.counting()));
				for (Long id : counting.keySet()) {
					namedMap.put(supplierRepository.findSupplierNameById(id), counting.get(id));
				}
			}

		} catch (Exception ex) {
			throw new ProductServiceException("exception in supplierWiseProductCount" + ex.getMessage());

		}

		return namedMap;
	}

}
