package au.com.anz.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import au.com.anz.model.Product;
@Repository("productRepository")
public interface ProductRepository extends CrudRepository<Product,Long>{

}
