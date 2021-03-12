package com.org.mortgageapp.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.org.mortgageapp.dto.PaymentDto;
import com.org.mortgageapp.dto.PaymentResponseDto;
import com.org.mortgageapp.exception.CustomException;
import com.org.mortgageapp.service.PaymentService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PaymentControllerTest {

	@Mock
	PaymentService paymentService;

	@InjectMocks
	PaymentController paymentController;

	@Test
	public void testPayEmi() throws CustomException {
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setEmi(3000.0);
		paymentDto.setPaymentType("Normal");
		PaymentResponseDto paymentResponseDto = new PaymentResponseDto("Sucess", 200);

		Mockito.when(paymentService.payEmi("123", paymentDto)).thenReturn(paymentResponseDto);
		ResponseEntity<PaymentResponseDto> result = paymentController.payEmiData("123", paymentDto);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

}