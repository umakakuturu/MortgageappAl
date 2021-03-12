package com.org.mortgageapp.service;

import com.org.mortgageapp.dto.PaymentDto;
import com.org.mortgageapp.dto.PaymentResponseDto;

public interface PaymentService {

	PaymentResponseDto payEmi(String customerId, PaymentDto paymentDto) ;

}
