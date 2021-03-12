package com.org.mortgageapp.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.org.mortgageapp.constant.UserUtility;
import com.org.mortgageapp.dto.MortgageReqDto;
import com.org.mortgageapp.dto.MortgageRequestDto;
import com.org.mortgageapp.dto.MortgageResDto;
import com.org.mortgageapp.dto.MortgageResponseDto;
import com.org.mortgageapp.entity.TransactionDetail;
import com.org.mortgageapp.exception.CustomException;
import com.org.mortgageapp.exception.NoDataException;
import com.org.mortgageapp.exception.PropertyDetailNotSaved;
import com.org.mortgageapp.exception.UserNotEligible;
import com.org.mortgageapp.service.MortgageService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class MortgageControllerTest {
	@InjectMocks
	MortgageController mortgageController;
	@Mock
	MortgageService mortgageService;

	@Test
	public void userLoginTest() {
		MortgageReqDto mortgageReqDto = new MortgageReqDto();
		mortgageReqDto.setCustomerId("15r6");
		mortgageReqDto.setPassword("uma");
		MortgageResDto mortgageResDto = new MortgageResDto();
		mortgageResDto.setStatusCode(UserUtility.INVALID_INPUT_CODE);
		mortgageResDto.setMessage(UserUtility.INVALID_INPUT);
		Mockito.when(mortgageService.userLogin(mortgageReqDto)).thenReturn(mortgageResDto);
		ResponseEntity<MortgageResDto> result = mortgageController.userLogin(mortgageReqDto);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void historyCheck() throws CustomException, NoDataException {
		List<TransactionDetail> transactionDetails = new ArrayList<TransactionDetail>();

		TransactionDetail detail = new TransactionDetail();
		detail.setDate(LocalDate.now());
		detail.setEmi(2000);
		detail.setTransactionId(1l);
		Mockito.when(mortgageService.fetchDetails("1")).thenReturn(transactionDetails);
		ResponseEntity<List<TransactionDetail>> result = mortgageController.
				getMortgageSummary("1");
		assertEquals(HttpStatus.OK, result.getStatusCode());

	}
	
	@Test
    public void applyMortgageTest() throws CustomException, UserNotEligible, PropertyDetailNotSaved {
        MortgageRequestDto mortgageRequestDto = new MortgageRequestDto();
        mortgageRequestDto.setPanCard("1123s");
        mortgageRequestDto.setPaymentType("autodebit");

 

        MortgageResponseDto mortgageResponseDto = new MortgageResponseDto();
        mortgageResponseDto.setMessage(UserUtility.APPLIED_SUCCESSSFULLY);
        mortgageResponseDto.setStatusCode(UserUtility.APPLIED_SUCCESSSFUL_STATUS);
        Mockito.when(mortgageService.applyMortgage(mortgageRequestDto)).thenReturn(mortgageResponseDto);
        ResponseEntity<MortgageResponseDto> result = mortgageController.applyMortgage(mortgageRequestDto);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}