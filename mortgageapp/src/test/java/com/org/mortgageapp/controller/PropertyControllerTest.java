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

import com.org.mortgageapp.constant.UserUtility;
import com.org.mortgageapp.dto.PropertyRequestDto;
import com.org.mortgageapp.dto.PropertyResponseDto;
import com.org.mortgageapp.exception.CustomException;
import com.org.mortgageapp.service.PropertyService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PropertyControllerTest {
	@InjectMocks
	PropertyController propertyController;
	@Mock
	PropertyService propertyService;

	@Test
	public void propertyDetailTest() throws CustomException {
		PropertyRequestDto propertyRequestDto = new PropertyRequestDto();
		propertyRequestDto.setInitialDeposit(100000.00);
		propertyRequestDto.setPropertyValue(12333788);
		propertyRequestDto.setTenure(4567);
		PropertyResponseDto propertyResponseDto = new PropertyResponseDto();
		propertyResponseDto.setEmi(5678);
		propertyResponseDto.setMessage(UserUtility.EMI_CALCULATED);
		propertyResponseDto.setStatusCode(UserUtility.EMI_CALCULATED_STATUS);
		Mockito.when(propertyService.propertyDetail(propertyRequestDto)).thenReturn(propertyResponseDto);
		ResponseEntity<PropertyResponseDto> result = propertyController.propertyDetail(propertyRequestDto);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
}
