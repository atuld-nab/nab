package au.com.anz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.anz.model.AccountTransaction;


public interface AccountTransactionRepository extends JpaRepository<AccountTransaction,Long>{

}
