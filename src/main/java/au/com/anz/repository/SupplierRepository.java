package au.com.anz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import au.com.anz.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier,Long> {
	@Query("Select supply.name from Supplier supply where supply.id= :id")
	String findSupplierNameById(@Param("id")Long id);
}
