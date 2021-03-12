package com.org.mortgageapp.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.org.mortgageapp.dto.PaymentDto;
import com.org.mortgageapp.dto.PaymentResponseDto;
import com.org.mortgageapp.entity.Mortgage;
import com.org.mortgageapp.entity.TransactionDetail;
import com.org.mortgageapp.exception.CustomException;
import com.org.mortgageapp.exception.PaymentException;
import com.org.mortgageapp.repository.MortgageRepository;
import com.org.mortgageapp.repository.TransactionRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PaymentServiceImplTest {
	@Mock
	MortgageRepository mortgageRepository;

	@Mock
	TransactionRepository transactionRepository;

	@InjectMocks
	PaymentServiceImpl paymentServiceImpl;

	@Test
	public void testPayEmi() throws CustomException {
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setEmi(3000.00);
		paymentDto.setPaymentType("Normal");

		Mortgage mortgage = new Mortgage();
		mortgage.setCustomerId("123");
		mortgage.setEmi(3000.00);

		TransactionDetail td = new TransactionDetail();
		td.setCustomerId("123");
		td.setEmi(3000.00);
		td.setDate(LocalDate.now());

		Mockito.when(mortgageRepository.fetchByCustomerId(Mockito.anyString())).thenReturn(mortgage);
		Mockito.spy(PaymentServiceImpl.class);
		Mockito.when(transactionRepository.save(td)).thenReturn(td);
		PaymentResponseDto result = paymentServiceImpl.payEmi("123", paymentDto);
		assertEquals(902, result.getStatusCode());
	}

	@Test(expected = PaymentException.class)
	public void testPayEmiNegative1() {
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setEmi(3000.00);
		paymentDto.setPaymentType(null);

		Mortgage mortgage = new Mortgage();
		mortgage.setCustomerId("123");
		mortgage.setEmi(3000.00);

		TransactionDetail td = new TransactionDetail();
		td.setCustomerId("123");
		td.setEmi(3000.00);
		td.setDate(LocalDate.now());

		Mockito.when(mortgageRepository.fetchByCustomerId(Mockito.anyString())).thenReturn(mortgage);
		Mockito.spy(PaymentServiceImpl.class);
		Mockito.when(transactionRepository.save(td)).thenReturn(td);
		PaymentResponseDto result = paymentServiceImpl.payEmi("123", paymentDto);
		assertEquals(200, result.getStatusCode());
	}

	@Test(expected = PaymentException.class)
	public void testPayEmiNegative2() {
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setEmi(3000.00);
		paymentDto.setPaymentType("Normal");

		Mortgage mortgage = new Mortgage();
		mortgage.setCustomerId("123");
		mortgage.setEmi(3000.00);

		TransactionDetail td = new TransactionDetail();
		td.setCustomerId("123");
		td.setEmi(3000.00);
		td.setDate(LocalDate.now());

		Mockito.when(mortgageRepository.fetchByCustomerId(Mockito.anyString())).thenReturn(mortgage);
		Mockito.spy(PaymentServiceImpl.class);
		Mockito.when(transactionRepository.save(td)).thenReturn(td);
		PaymentResponseDto result = paymentServiceImpl.payEmi(null, paymentDto);
		assertEquals(608, result.getStatusCode());
	}

	@Test(expected = PaymentException.class)
	public void testPayEmiNegative3() {
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setEmi(3000.00);
		paymentDto.setPaymentType("Normal");

		Mortgage mortgage = new Mortgage();
		mortgage.setCustomerId("123");
		mortgage.setEmi(3000.00);

		TransactionDetail td = new TransactionDetail();
		td.setCustomerId("123");
		td.setEmi(3000.00);
		td.setDate(LocalDate.now());

		Mockito.when(mortgageRepository.fetchByCustomerId(null)).thenReturn(mortgage);
		Mockito.spy(PaymentServiceImpl.class);
		Mockito.when(transactionRepository.save(td)).thenReturn(td);
		PaymentResponseDto result = paymentServiceImpl.payEmi("123", paymentDto);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

}
