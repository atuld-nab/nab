package au.com.nab.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import au.com.nab.model.Customer;
@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer,Long> {
	@Query("SELECT cust FROM Customer cust  WHERE cust.custName= (:custName)")
    Customer findByName( @Param("custName") String custName);
	@Query( value = "select * from Customer p " +
            "join Contact b " +
            "   on p.id = b.customer_id " +
            "where b.msisdn = :msisdn", nativeQuery = true)
    Customer enableContact(@Param("msisdn") String msisdn);


}
