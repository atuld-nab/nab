package au.com.anz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import au.com.anz.model.Brand;

public interface BrandRepository extends JpaRepository<Brand,Long>{
	@Query("Select b.name from Brand b where b.id= :id")
	String findBrandNameById(@Param("id")Long id);
}
