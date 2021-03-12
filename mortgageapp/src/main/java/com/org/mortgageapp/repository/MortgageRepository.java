package com.org.mortgageapp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.org.mortgageapp.entity.Mortgage;
import com.org.mortgageapp.entity.TransactionDetail;

@Transactional
@Repository
public interface MortgageRepository extends JpaRepository<Mortgage, Long> {

	Mortgage findByCustomerIdAndPassword(String customerId, String password);

	@Query(nativeQuery = true, value = "SELECT * FROM transaction_detail  WHERE customer_Id=?1 ")
	public List<TransactionDetail> searchByCustomerId(String customerId);

	@Query("Select entity from Mortgage entity where entity.customerId =?1")
	public Mortgage fetchByCustomerId(String customerId);

	@Modifying
	@Query("Update Mortgage entity SET entity.mortgageAccountBalance =?1 where entity.customerId =?2")
	public void updateMortgageAccountBalance(double updatedMortgageAccountBalance, String customerId);

	@Modifying
	@Query("Update Mortgage entity SET entity.savingsAccountBalance =?1 where entity.customerId =?2")
	public void updateSavingAccountBalance(double updatedMortgageAccountBalance, String customerId);

	@Query("Select entity from Mortgage entity where entity.paymentType =?1")
	public List<Mortgage> fetchAllCustomersWhosePaymentTypeIsAuto(String string);

	List<TransactionDetail> findByCustomerId(String customerId);

}
