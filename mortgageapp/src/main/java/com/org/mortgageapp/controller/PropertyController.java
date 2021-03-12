package com.org.mortgageapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.mortgageapp.dto.PropertyRequestDto;
import com.org.mortgageapp.dto.PropertyResponseDto;
import com.org.mortgageapp.exception.CustomException;
import com.org.mortgageapp.service.PropertyService;

@RestController
@CrossOrigin(origins ="*", allowedHeaders= "*")
public class PropertyController {

	@Autowired
	PropertyService propertyService;
	
	@PostMapping("/property")
	public ResponseEntity<PropertyResponseDto> propertyDetail(@Valid @RequestBody PropertyRequestDto propertyRequestDto)
			throws CustomException{
		PropertyResponseDto propertyResponseDto = propertyService.propertyDetail(propertyRequestDto);
		return new ResponseEntity<>(propertyResponseDto, HttpStatus.OK);
	}
}
