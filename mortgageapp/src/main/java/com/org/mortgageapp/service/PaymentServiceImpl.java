package com.org.mortgageapp.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.mortgageapp.constant.UserUtility;
import com.org.mortgageapp.dto.PaymentDto;
import com.org.mortgageapp.dto.PaymentResponseDto;
import com.org.mortgageapp.entity.Mortgage;
import com.org.mortgageapp.entity.TransactionDetail;
import com.org.mortgageapp.exception.PaymentException;
import com.org.mortgageapp.repository.MortgageRepository;
import com.org.mortgageapp.repository.TransactionRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	SchedulerServiceImpl schedulerServiceImpl;

	@Autowired
	MortgageRepository mortgageRepository;

	@Autowired
	TransactionRepository transactionRepository;

	private static Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

	/**
	 * 
	 *  
	 * @Author ALok As part of this API a person can pay the emi and that amount
	 *         will get deducted from his/her saving account and gets added in
	 *         mortgage account, also the transaction details will stored in
	 *         transaction table.
	 * @throws PaymentException
	 */

	@Override
	public PaymentResponseDto payEmi(String customerId, PaymentDto paymentDto) {
		log.info("ENTER ::public PaymentResponseDto payEmi(String customerId, PaymentDto paymentDto)");
		TransactionDetail transactionDetail = new TransactionDetail();

		if (customerId == null) {
			throw new PaymentException(UserUtility.USER_NOT_EXIST, UserUtility.USER_NOT_EXIST_STATUS);
		}
		if (paymentDto.getPaymentType() == null) {
			throw new PaymentException(UserUtility.PAYMENT_TYPE_NULL, UserUtility.PAYMENT_TYPE_NULL_STATUS);
		}
		if (paymentDto.getPaymentType().equalsIgnoreCase("Normal")) {

			Mortgage mortgage = mortgageRepository.fetchByCustomerId(customerId);
			if (mortgage == null) {
				throw new PaymentException(UserUtility.NO_MORTGAGE_DETAIL_FOUND,
						UserUtility.NO_MORTGAGE_DETAIL_FOUND_STATUS);
			} else {
				double updatedMortgageAccountBalance = mortgage.getMortgageAccountBalance() + paymentDto.getEmi();
				double updatedSavingAccountBalance = mortgage.getSavingsAccountBalance() - paymentDto.getEmi();
				mortgageRepository.updateMortgageAccountBalance(updatedMortgageAccountBalance, customerId);
				mortgageRepository.updateSavingAccountBalance(updatedSavingAccountBalance, customerId);
			}
			transactionDetail.setCustomerId(customerId);
			transactionDetail.setEmi(paymentDto.getEmi());
			transactionDetail.setDate(LocalDate.now());

			transactionRepository.save(transactionDetail);

		}
		log.info("EXIT ::public PaymentResponseDto payEmi(String customerId, PaymentDto paymentDto)");
		return new PaymentResponseDto("Transaction Data Successfully Saved", 902);

	}

}
