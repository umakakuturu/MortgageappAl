package com.org.mortgageapp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.mortgageapp.constant.UserUtility;
import com.org.mortgageapp.dto.PropertyRequestDto;
import com.org.mortgageapp.dto.PropertyResponseDto;
import com.org.mortgageapp.entity.Property;
import com.org.mortgageapp.entity.User;
import com.org.mortgageapp.exception.CustomException;
import com.org.mortgageapp.repository.PropertyRepository;
import com.org.mortgageapp.repository.UserRepository;

@Service
public class PropertyServiceImpl implements PropertyService{

	@Autowired
	PropertyRepository propertyRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public PropertyResponseDto propertyDetail(PropertyRequestDto propertyRequestDto) throws CustomException {
		User user = userRepository.findByPanCard(propertyRequestDto.getPanCard());
		if(user != null) {
		Property property = new Property();
		BeanUtils.copyProperties(propertyRequestDto, property);
		property.setUser(user);
		PropertyResponseDto propertyResponseDto = new PropertyResponseDto();
		double emi = (property.getPropertyValue()-property.getInitialDeposit())/property.getTenure();
		property.setEmi(emi);
		propertyRepository.save(property);
		propertyResponseDto.setEmi(emi);
		propertyResponseDto.setMessage(UserUtility.EMI_CALCULATED);
		propertyResponseDto.setStatusCode(UserUtility.EMI_CALCULATED_STATUS);
		return propertyResponseDto;
		
		}
		else {
			throw new CustomException(UserUtility.USER_NOT_EXIST);
		}
	}

}
