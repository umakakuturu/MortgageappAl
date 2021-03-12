package com.org.mortgageapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.org.mortgageapp.entity.TransactionDetail;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDetail, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM transaction_detail  WHERE customer_Id=?1 LIMIT 10")
	public List<TransactionDetail> searchByCustomerId(String customerId);

}
