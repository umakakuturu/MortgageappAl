package com.org.mortgageapp.service;

import com.org.mortgageapp.dto.PropertyRequestDto;
import com.org.mortgageapp.dto.PropertyResponseDto;
import com.org.mortgageapp.exception.CustomException;

public interface PropertyService {

	PropertyResponseDto propertyDetail(PropertyRequestDto propertyRequestDto) throws CustomException;

}
