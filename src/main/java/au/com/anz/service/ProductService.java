package au.com.anz.service;

import java.util.List;
import java.util.Map;

import au.com.anz.Exception.ProductServiceException;
import au.com.anz.model.Product;

public interface ProductService {
    public Map<String,List<Product>> groupByColor() throws ProductServiceException;
    public Map<String,List<Product>> groupByBrand() throws ProductServiceException;
    public Map<Double,List<Product>> groupByPrice() throws ProductServiceException;
    public Map<String,List<Product>> groupBySize() throws ProductServiceException;
    public Map<String,Long> supplierWiseProductCount() throws ProductServiceException;
    public Product findBySkuCode(final Long skuCode) throws ProductServiceException;
   }
