package com.org.mortgageapp.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.mortgageapp.constant.UserUtility;
import com.org.mortgageapp.dto.MortgageReqDto;
import com.org.mortgageapp.dto.MortgageRequestDto;
import com.org.mortgageapp.dto.MortgageResDto;
import com.org.mortgageapp.dto.MortgageResponseDto;
import com.org.mortgageapp.entity.Mortgage;
import com.org.mortgageapp.entity.Property;
import com.org.mortgageapp.entity.TransactionDetail;
import com.org.mortgageapp.entity.User;
import com.org.mortgageapp.exception.CustomException;
import com.org.mortgageapp.exception.NoDataException;
import com.org.mortgageapp.exception.PropertyDetailNotSaved;
import com.org.mortgageapp.exception.UserNotEligible;
import com.org.mortgageapp.repository.MortgageRepository;
import com.org.mortgageapp.repository.PropertyRepository;
import com.org.mortgageapp.repository.TransactionRepository;
import com.org.mortgageapp.repository.UserRepository;

@Service
public class MortgageServiceImpl implements MortgageService {

	@Autowired
	MortgageRepository mortgageRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PropertyRepository propertyRepository;
	
	@Autowired
	TransactionRepository transactionRepository;

	/**
	 * 
	 * @author Koushik
	 * @since 30-03-2020
	 * 
	 *        In this Functionality we are trying to apply for the Mortgage
	 * 
	 * @param mortgageRequestDto
	 * @return
	 * @throws CustomException
	 * @throws UserNotEligible
	 * @throws PropertyDetailNotSaved
	 */
	@SuppressWarnings("unused")
	@Override
	public MortgageResponseDto applyMortgage(@Valid MortgageRequestDto mortgageRequestDto)
			throws CustomException, UserNotEligible, PropertyDetailNotSaved {
		User user = userRepository.findByPanCard(mortgageRequestDto.getPanCard());
		if (user == null) {
			throw new CustomException(UserUtility.USER_NOT_EXIST);
		}
		Property property = propertyRepository.findByUserPanCard(mortgageRequestDto.getPanCard());
		
		if (property != null) {
			if ((property.getEmi() >= (0.7 * user.getIncome()))) {

				throw new UserNotEligible(UserUtility.USER_NOT_ELIGIBLE);
			}

			Mortgage mortgage = new Mortgage();
			mortgage.setUser(user);
			MortgageResponseDto mortgageResponseDto = new MortgageResponseDto();
			mortgageResponseDto.setCustomerId("MT" + mortgageRequestDto.getPanCard());
			mortgageResponseDto.setPassword(user.getUserName() + "123");
			mortgageResponseDto.setSavingsAccountNumber("SA" + mortgageRequestDto.getPanCard());
			mortgageResponseDto.setMortgageAccountNumber("MT" + mortgageRequestDto.getPanCard());

			double savingAccountBalance = property.getPropertyValue() - property.getInitialDeposit();
			mortgageResponseDto.setSavingsAccountBalance(savingAccountBalance);

			double mortgageAccountBalance = -(property.getPropertyValue() - property.getInitialDeposit());
			mortgageResponseDto.setMortgageAccountBalance(mortgageAccountBalance);

			mortgageResponseDto.setEmi(property.getEmi());
			mortgageResponseDto.setPaymentType(mortgageRequestDto.getPaymentType());
			mortgageResponseDto.setMessage(UserUtility.APPLIED_SUCCESSSFULLY);
			mortgageResponseDto.setStatusCode(UserUtility.APPLIED_SUCCESSSFUL_STATUS);
			BeanUtils.copyProperties(mortgageResponseDto, mortgage);
			mortgageRepository.save(mortgage);

			return mortgageResponseDto;
		} else {
			throw new PropertyDetailNotSaved(UserUtility.PROPERTY_DETAIL_NOT_SAVED);
		}
	}

	/**
	 * This method is used to check the login details of the user
	 * 
	 * @author uma
	 * @since 2020-03-30
	 * @param customerId -Here we use customerId to check the customerid is correct
	 *                   or not
	 * @param password   -Here we use password to check the password correct or not
	 * @return ResponseEntity Object along with status code and success login
	 *         message
	 * 
	 */
	@Override
	public MortgageResDto userLogin(MortgageReqDto mortgageReqDto) {
		MortgageResDto mortgageResDto = new MortgageResDto();
		if (mortgageReqDto.getCustomerId().isEmpty() || mortgageReqDto.getPassword().isEmpty()) {
			mortgageResDto.setStatusCode(UserUtility.INVALID_INPUT_CODE);
			mortgageResDto.setMessage(UserUtility.INVALID_INPUT);

		} else {
			Mortgage mortgage = mortgageRepository.findByCustomerIdAndPassword(mortgageReqDto.getCustomerId(),
					mortgageReqDto.getPassword());

			if (mortgage != null) {

				mortgageResDto.setCustomerId(mortgage.getCustomerId());
				mortgageResDto.setMortgageAccountNumber(mortgage.getMortgageAccountNumber());
				mortgageResDto.setMortgageAccountBalance(mortgage.getMortgageAccountBalance());
				mortgageResDto.setSavingsAccountBalance(mortgage.getSavingsAccountBalance());
				mortgageResDto.setSavingsAccountNumber(mortgage.getSavingsAccountNumber());
				mortgageResDto.setEmi(mortgage.getEmi());
				mortgageResDto.setMessage(UserUtility.LOGIN_SUCCESS);
				mortgageResDto.setStatusCode(UserUtility.LOGIN_SUCCESS_CODE);

			} else {

				mortgageResDto.setStatusCode(UserUtility.LOGIN_FAIL_CODE);
				mortgageResDto.setMessage(UserUtility.LOGIN_FAIL);

			}
		}
		return mortgageResDto;

	}

	/**
	 * 
	 * this service will fetch the transaction history of the given customer
	 * 
	 * @Pathvariable customerId
	 * 
	 *               returning list of details of transaction
	 * 
	 *               return error with message and status code
	 * 
	 */
	@Override
	public List<TransactionDetail> fetchDetails(String customerId) throws CustomException, NoDataException {
		
		List<TransactionDetail> customer = transactionRepository.
				searchByCustomerId(customerId);

		
		if (customer.isEmpty()) {
			throw new CustomException(UserUtility.NO_DATA_FOUND);
		}
		return customer;

	}

}
