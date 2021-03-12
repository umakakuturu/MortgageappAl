package com.org.mortgageapp.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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

@RunWith(MockitoJUnitRunner.Silent.class)
public class MortgageServiceImplTest {
	@InjectMocks
	MortgageServiceImpl mortgageServiceImpl;
	@Mock
	MortgageRepository mortgageRepository;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	PropertyRepository propertyRepository;
	
	@Mock
	TransactionRepository transactionRepository;

	@Test
	public void userLoginTest() {
		MortgageReqDto mortgageReqDto = new MortgageReqDto();

		mortgageReqDto.setCustomerId("15r6");
		mortgageReqDto.setPassword("Uma");
		MortgageResDto mortgageResDto = new MortgageResDto();
		mortgageResDto.setStatusCode(UserUtility.LOGIN_SUCCESS_CODE);
		mortgageResDto.setMessage("Logged in successfully!!..");

		Mortgage mortgage = new Mortgage();
		mortgage.setCustomerId("15r6");
		mortgage.setMortgageAccountBalance(12334);
		mortgage.setPassword("uma");

		Mockito.when(mortgageRepository.findByCustomerIdAndPassword(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(mortgage);
		MortgageResDto result = mortgageServiceImpl.userLogin(mortgageReqDto);

		assertEquals(mortgageResDto.getStatusCode(), result.getStatusCode());
	}

	@Test
	public void InvalidUserLoginTest() {

		MortgageReqDto mortgageReqDto = new MortgageReqDto();

		mortgageReqDto.setCustomerId("");
		mortgageReqDto.setPassword("");
		MortgageResDto mortgageResDto = new MortgageResDto();
		mortgageResDto.setStatusCode(UserUtility.INVALID_INPUT_CODE);
		mortgageResDto.setMessage("Please provide the required  data");

		Mortgage mortgage = new Mortgage();
		mortgage.setCustomerId("15r6");
		mortgage.setMortgageAccountBalance(12334);
		mortgage.setPassword("uma");
		Mockito.when(mortgageRepository.findByCustomerIdAndPassword(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(mortgage);
		MortgageResDto result = mortgageServiceImpl.userLogin(mortgageReqDto);

		assertEquals(mortgageResDto.getStatusCode(), result.getStatusCode());

	}

	@Test
	public void InvalidUserLoginUserTest() {

		MortgageReqDto mortgageReqDto = new MortgageReqDto();

		mortgageReqDto.setCustomerId("");
		mortgageReqDto.setPassword("Uma");
		MortgageResDto mortgageResDto = new MortgageResDto();
		mortgageResDto.setStatusCode(UserUtility.INVALID_INPUT_CODE);
		mortgageResDto.setMessage("Please provide the required  data");

		Mortgage mortgage = new Mortgage();
		mortgage.setCustomerId("15r6");
		mortgage.setMortgageAccountBalance(12334);
		mortgage.setPassword("uma");
		Mockito.when(mortgageRepository.findByCustomerIdAndPassword(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(mortgage);
		MortgageResDto result = mortgageServiceImpl.userLogin(mortgageReqDto);

		assertEquals(mortgageResDto.getStatusCode(), result.getStatusCode());

	}

	@Test
	public void InvalidUserLoginPasswordTest() {

		MortgageReqDto mortgageReqDto = new MortgageReqDto();

		mortgageReqDto.setCustomerId("15r6");
		mortgageReqDto.setPassword("");
		MortgageResDto mortgageResDto = new MortgageResDto();
		mortgageResDto.setStatusCode(UserUtility.INVALID_INPUT_CODE);
		mortgageResDto.setMessage("Please provide the required  data");

		Mortgage mortgage = new Mortgage();
		mortgage.setCustomerId("15r6");
		mortgage.setMortgageAccountBalance(12334);
		mortgage.setPassword("uma");
		Mockito.when(mortgageRepository.findByCustomerIdAndPassword(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(mortgage);
		MortgageResDto result = mortgageServiceImpl.userLogin(mortgageReqDto);

		assertEquals(mortgageResDto.getStatusCode(), result.getStatusCode());

	}

	@Test
	public void NoUserLoginTest() {
		MortgageReqDto mortgageReqDto = new MortgageReqDto();

		mortgageReqDto.setCustomerId("15r6");
		mortgageReqDto.setPassword("Uma");

		MortgageResDto mortgageResDto = new MortgageResDto();
		mortgageResDto.setStatusCode(UserUtility.LOGIN_FAIL_CODE);
		mortgageResDto.setMessage("Please Register");

		Mortgage mortgage = new Mortgage();
		mortgage.setCustomerId("15r6");
		mortgage.setMortgageAccountBalance(12334);

		Mockito.when(mortgageRepository.findByCustomerIdAndPassword("15r6", "uma")).thenReturn(mortgage);
		MortgageResDto result = mortgageServiceImpl.userLogin(mortgageReqDto);

		assertEquals(mortgageResDto.getStatusCode(), result.getStatusCode());

	}

	@Test
	public void applyMortgageTest() throws CustomException, UserNotEligible, PropertyDetailNotSaved {
		MortgageRequestDto mortgageRequestDto = new MortgageRequestDto();
		mortgageRequestDto.setPanCard("string");
		mortgageRequestDto.setPaymentType("string");
		MortgageResponseDto mortgageResponseDto = new MortgageResponseDto();
		mortgageResponseDto.setCustomerId("123qw");
		mortgageResponseDto.setMortgageAccountBalance(123443);
		mortgageResponseDto.setPassword("uma");
		mortgageResponseDto.setMessage(UserUtility.APPLIED_SUCCESSSFULLY);
		mortgageResponseDto.setStatusCode(UserUtility.APPLIED_SUCCESSSFUL_STATUS);

		Property property = new Property();
		property.setEmi(2000.00);
		User user = new User();
		user.setUserName("uma");
		user.setAddress("bnglr");
		user.setIncome(10000);
		Mockito.when(userRepository.findByPanCard(Mockito.anyString())).thenReturn(user);
		Mockito.when(propertyRepository.findByUserPanCard(Mockito.anyString())).thenReturn(property);
		MortgageResponseDto result = mortgageServiceImpl.applyMortgage(mortgageRequestDto);
		assertEquals(mortgageResponseDto.getStatusCode(), result.getStatusCode());

	}

	@Test(expected = CustomException.class)
	public void applyMortgageNegativeTest() throws CustomException, UserNotEligible, PropertyDetailNotSaved {
		MortgageRequestDto mortgageRequestDto = new MortgageRequestDto();
		mortgageRequestDto.setPanCard("string");
		mortgageRequestDto.setPaymentType("string");
		MortgageResponseDto mortgageResponseDto = new MortgageResponseDto();
		mortgageResponseDto.setCustomerId("123qw");
		mortgageResponseDto.setMortgageAccountBalance(123443);
		mortgageResponseDto.setPassword("uma");
		mortgageResponseDto.setMessage(UserUtility.APPLIED_SUCCESSSFULLY);
		mortgageResponseDto.setStatusCode(UserUtility.APPLIED_SUCCESSSFUL_STATUS);

		Property property = new Property();
		property.setEmi(2000.00);
		User user = new User();
		user.setUserName("uma");
		user.setAddress("bnglr");
		user.setIncome(10000);
		Mockito.when(userRepository.findByPanCard(Mockito.anyString())).thenReturn(null);
		Mockito.when(propertyRepository.findByUserPanCard(Mockito.anyString())).thenReturn(property);
		MortgageResponseDto result = mortgageServiceImpl.applyMortgage(mortgageRequestDto);
		assertEquals(mortgageResponseDto.getStatusCode(), result.getStatusCode());

	}
	
	@Test(expected = UserNotEligible.class)
	public void applyMortgageTest2() throws CustomException, UserNotEligible, PropertyDetailNotSaved {
		MortgageRequestDto mortgageRequestDto = new MortgageRequestDto();
		mortgageRequestDto.setPanCard("string");
		mortgageRequestDto.setPaymentType("string");
		MortgageResponseDto mortgageResponseDto = new MortgageResponseDto();
		mortgageResponseDto.setCustomerId("123qw");
		mortgageResponseDto.setMortgageAccountBalance(123443);
		mortgageResponseDto.setPassword("uma");
		mortgageResponseDto.setMessage(UserUtility.APPLIED_SUCCESSSFULLY);
		mortgageResponseDto.setStatusCode(UserUtility.APPLIED_SUCCESSSFUL_STATUS);

		Property property = new Property();
		property.setEmi(20000.00);
		User user = new User();
		user.setUserName("uma");
		user.setAddress("bnglr");
		user.setIncome(10000);
		Mockito.when(userRepository.findByPanCard(Mockito.anyString())).thenReturn(user);
		Mockito.when(propertyRepository.findByUserPanCard(Mockito.anyString())).thenReturn(property);
		MortgageResponseDto result = mortgageServiceImpl.applyMortgage(mortgageRequestDto);
		assertEquals(mortgageResponseDto.getStatusCode(), result.getStatusCode());

	}
	
	@Test(expected = PropertyDetailNotSaved.class)
	public void applyMortgageTest3() throws CustomException, UserNotEligible, PropertyDetailNotSaved {
		MortgageRequestDto mortgageRequestDto = new MortgageRequestDto();
		mortgageRequestDto.setPanCard("string");
		mortgageRequestDto.setPaymentType("string");
		MortgageResponseDto mortgageResponseDto = new MortgageResponseDto();
		mortgageResponseDto.setCustomerId("123qw");
		mortgageResponseDto.setMortgageAccountBalance(123443);
		mortgageResponseDto.setPassword("uma");
		mortgageResponseDto.setMessage(UserUtility.APPLIED_SUCCESSSFULLY);
		mortgageResponseDto.setStatusCode(UserUtility.APPLIED_SUCCESSSFUL_STATUS);

		Property property = new Property();
		property.setEmi(20000.00);
		User user = new User();
		user.setUserName("uma");
		user.setAddress("bnglr");
		user.setIncome(10000);
		Mockito.when(userRepository.findByPanCard(Mockito.anyString())).thenReturn(user);
		Mockito.when(propertyRepository.findByUserPanCard(Mockito.anyString())).thenReturn(null);
		MortgageResponseDto result = mortgageServiceImpl.applyMortgage(mortgageRequestDto);
		assertEquals(mortgageResponseDto.getStatusCode(), result.getStatusCode());

	}


	
	@Test(expected = Exception.class)
	public void historyCheck() throws CustomException, NoDataException {
		TransactionDetail transactionDetail = new TransactionDetail();
		transactionDetail.setCustomerId("1");
		List<TransactionDetail> history = new ArrayList<TransactionDetail>();
		transactionDetail.setDate(LocalDate.now());
		transactionDetail.setEmi(2000);
		transactionDetail.setTransactionId(1l);
		history.add(transactionDetail);
		Mockito.when(mortgageRepository.searchByCustomerId(transactionDetail.getCustomerId())).
		thenReturn(history);
		mortgageServiceImpl.fetchDetails("1");
		assertEquals(1, history.size());

	}

	@Test(expected = CustomException.class)
	public void historyCheck1() throws CustomException, NoDataException, NullPointerException {

		TransactionDetail transactionDetail = new TransactionDetail();
		transactionDetail.setCustomerId("1");

		transactionDetail.setDate(LocalDate.now());
		transactionDetail.setEmi(2000);
		transactionDetail.setTransactionId(1l);

		Mockito.when(mortgageRepository.fetchByCustomerId(transactionDetail.getCustomerId())).
		thenReturn(null);

		mortgageServiceImpl.fetchDetails("1");
	}

	@Test
	public void fetchDetails() throws CustomException, NoDataException {
		TransactionDetail td = new TransactionDetail();
		List<TransactionDetail> list = new ArrayList<>();
		list.add(td);
		Mockito.when(transactionRepository.searchByCustomerId(Mockito.anyString())).thenReturn(list);
		List<TransactionDetail> lt = mortgageServiceImpl.fetchDetails("1");
		assertEquals(1, lt.size());
	}
}
