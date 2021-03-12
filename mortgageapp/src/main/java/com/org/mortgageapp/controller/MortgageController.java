package com.org.mortgageapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
/**
 * 
 * @author Koushik
 * @since 30-03-2020
 *
 */
public class MortgageController {

	@Autowired
	MortgageService mortgageService;

	/**
	 * In this Functionality we are trying to apply for the Mortgage
	 * 
	 * @param mortgageRequestDto
	 * @return
	 * @throws CustomException
	 * @throws UserNotEligible
	 * @throws PropertyDetailNotSaved
	 */
	@PostMapping("/apply")
	public ResponseEntity<MortgageResponseDto> applyMortgage(@Valid @RequestBody MortgageRequestDto mortgageRequestDto)
			throws CustomException, UserNotEligible, PropertyDetailNotSaved {
		MortgageResponseDto mortgageResponseDto = mortgageService.applyMortgage(mortgageRequestDto);
		return new ResponseEntity<>(mortgageResponseDto, HttpStatus.OK);

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

	@PostMapping("login")
	public ResponseEntity<MortgageResDto> userLogin(@RequestBody MortgageReqDto mortgageReqDto) {
		MortgageResDto mortgageResDto = mortgageService.userLogin(mortgageReqDto);

		return new ResponseEntity<>(mortgageResDto, HttpStatus.OK);
	}

	/**
	 * 
	 * @author ashan
	 * 
	 *         this method will fetch the transaction history of the given customer
	 * 
	 * @Pathvariable customerId
	 * 
	 * @return returning list of details of transaction
	 * 
	 *         return error with message and status code
	 * 
	 */

	@GetMapping("/mortgage")
	public ResponseEntity<List<TransactionDetail>> getMortgageSummary(@RequestParam("customerId") String customerId)
			throws CustomException, NoDataException {
		List<TransactionDetail> transactionDetails = mortgageService.fetchDetails(customerId);

		return new ResponseEntity<>(transactionDetails, HttpStatus.OK);

	}

}
