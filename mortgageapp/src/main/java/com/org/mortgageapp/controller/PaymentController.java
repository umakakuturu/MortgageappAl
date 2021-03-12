package com.org.mortgageapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.mortgageapp.dto.PaymentDto;
import com.org.mortgageapp.dto.PaymentResponseDto;
import com.org.mortgageapp.service.PaymentService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	@PutMapping("/payment")
	public ResponseEntity<PaymentResponseDto> payEmiData(@RequestParam("customerId") String customerId,
			@RequestBody PaymentDto paymentDto) {
		PaymentResponseDto paymentResponseDto = paymentService.payEmi(customerId, paymentDto);
		return new ResponseEntity<>(paymentResponseDto, HttpStatus.OK);
	}

}
