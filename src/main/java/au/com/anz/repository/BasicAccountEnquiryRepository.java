package au.com.anz.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import au.com.anz.model.BasicAccountEnquiry;

@Repository
@Transactional
public interface BasicAccountEnquiryRepository extends JpaRepository<BasicAccountEnquiry,Long>{
	@Query("select b from BasicAccountEnquiry b " +
            "where b.accountNumber = (:accountNumber)")
	BasicAccountEnquiry findByAccountNumber(@Param("accountNumber") String accountNumber);
}
