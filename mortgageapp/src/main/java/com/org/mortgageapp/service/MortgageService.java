package com.org.mortgageapp.service;

import java.util.List;

import javax.validation.Valid;

import com.org.mortgageapp.dto.MortgageReqDto;
import com.org.mortgageapp.dto.MortgageRequestDto;
import com.org.mortgageapp.dto.MortgageResDto;
import com.org.mortgageapp.dto.MortgageResponseDto;
import com.org.mortgageapp.entity.TransactionDetail;
import com.org.mortgageapp.exception.CustomException;
import com.org.mortgageapp.exception.NoDataException;
import com.org.mortgageapp.exception.PropertyDetailNotSaved;
import com.org.mortgageapp.exception.UserNotEligible;

public interface MortgageService {

	MortgageResponseDto applyMortgage(@Valid MortgageRequestDto mortgageRequestDto) 
			throws CustomException, UserNotEligible, PropertyDetailNotSaved;

	MortgageResDto userLogin(MortgageReqDto mortgageReqDto);
	
	 List<TransactionDetail> fetchDetails(String customerId)
			 throws CustomException, NoDataException;

}
