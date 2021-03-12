package com.org.mortgageapp.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.org.mortgageapp.entity.Mortgage;
import com.org.mortgageapp.entity.TransactionDetail;
import com.org.mortgageapp.exception.PaymentException;
import com.org.mortgageapp.repository.MortgageRepository;
import com.org.mortgageapp.repository.TransactionRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SchedulerServiceTest {
	@Mock
	MortgageRepository mortgageRepository;
	@Mock
	TransactionRepository transactionRepository;

	@InjectMocks
	SchedulerServiceImpl schedulerServiceImpl;

	@Test
	public void testAuto() {

		Mortgage mortgage = new Mortgage();
		List<Mortgage> ls = new ArrayList<Mortgage>();
		ls.add(mortgage);

		TransactionDetail td = new TransactionDetail();
		td.setCustomerId("123");
		td.setEmi(3000.00);
		Mockito.when(mortgageRepository.fetchAllCustomersWhosePaymentTypeIsAuto(Mockito.anyString())).
		thenReturn(ls);
		Mockito.when(transactionRepository.save(td)).thenReturn(td);
		schedulerServiceImpl.payAutoEmi();

	}

	@Test(expected = PaymentException.class)
	public void testAutoNegative() {

		Mortgage mortgage = new Mortgage();
		List<Mortgage> ls = new ArrayList<Mortgage>();
		ls.add(mortgage);

		TransactionDetail td = new TransactionDetail();
		td.setCustomerId("123");
		td.setEmi(3000.00);
		Mockito.when(mortgageRepository.fetchAllCustomersWhosePaymentTypeIsAuto(Mockito.anyString())).
		thenReturn(null);
		Mockito.spy(SchedulerServiceImpl.class);
		Mockito.when(transactionRepository.save(td)).thenReturn(td);
		schedulerServiceImpl.payAutoEmi();

	}

}