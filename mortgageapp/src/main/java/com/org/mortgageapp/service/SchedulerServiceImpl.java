package com.org.mortgageapp.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.org.mortgageapp.constant.UserUtility;
import com.org.mortgageapp.entity.Mortgage;
import com.org.mortgageapp.entity.TransactionDetail;
import com.org.mortgageapp.exception.PaymentException;
import com.org.mortgageapp.repository.MortgageRepository;
import com.org.mortgageapp.repository.TransactionRepository;

@Component
public class SchedulerServiceImpl {

	private static Logger log = LoggerFactory.getLogger(SchedulerServiceImpl.class);

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	MortgageRepository mortgageRepository;

	/**
	 * @author Alok As part of this scheduler if any customer's payment type is auto
	 *         it will automatically deduct from the person's account.
	 * @throws Payment Exception
	 */

	@Scheduled(cron = "0 */20 * * * ?")
	public void payAutoEmi() {

		log.info("ENTER :: public void payAutoEmi() ");
		List<Mortgage> list = mortgageRepository.fetchAllCustomersWhosePaymentTypeIsAuto("auto");

		if (list != null) {
			list.forEach(entity -> {
				TransactionDetail transactionDetail = new TransactionDetail();
				double updatedMortgageAccountBalance = entity.getMortgageAccountBalance() + entity.getEmi();
				double updatedSavingAccountBalance = entity.getSavingsAccountBalance() - entity.getEmi();
				mortgageRepository.updateMortgageAccountBalance(updatedMortgageAccountBalance, 
						entity.getCustomerId());
				mortgageRepository.updateSavingAccountBalance(updatedSavingAccountBalance,
						entity.getCustomerId());
				transactionDetail.setCustomerId(entity.getCustomerId());
				transactionDetail.setEmi(entity.getEmi());
				transactionDetail.setDate(LocalDate.now());
				transactionRepository.save(transactionDetail);
			});
		} else {
			throw new PaymentException(UserUtility.NO_USER_WITH_AUTO, UserUtility.NO_USER_WITH_AUTO_CODE);
		}

		log.info("EXIT :: public void payAutoEmi() ");
	}

}
