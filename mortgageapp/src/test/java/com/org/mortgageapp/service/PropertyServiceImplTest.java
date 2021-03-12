package com.org.mortgageapp.service;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.org.mortgageapp.constant.UserUtility;
import com.org.mortgageapp.dto.PropertyRequestDto;
import com.org.mortgageapp.dto.PropertyResponseDto;
import com.org.mortgageapp.entity.Property;
import com.org.mortgageapp.entity.User;
import com.org.mortgageapp.exception.CustomException;
import com.org.mortgageapp.repository.PropertyRepository;
import com.org.mortgageapp.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PropertyServiceImplTest {
	@InjectMocks
	PropertyServiceImpl propertyServiceImpl;
	@Mock
	PropertyRepository propertyRepository;
	@Mock
	UserRepository userRepository;

	@Test
	public void propertyDetailsuccessTest() throws CustomException {
		PropertyRequestDto propertyRequestDto = new PropertyRequestDto();
		propertyRequestDto.setPanCard("");
//		propertyRequestDto.setInitialDeposit(10000.00);
//		propertyRequestDto.setPropertyValue(2334555);

		PropertyResponseDto propertyResponseDto = new PropertyResponseDto();

		propertyResponseDto.setStatusCode(UserUtility.USER_NOT_EXIST_STATUS);
		User user = new User();
//		user.setEmailId("");
//		user.setIncome(100000.00);
//		user.setPanCard("");

		Property property = new Property();
		property.setEmi(1234);
		property.setPropertyId(112223);

		Mockito.when(userRepository.findByPanCard(Mockito.anyString())).thenReturn(user);
		Mockito.when(propertyRepository.save(property)).thenReturn(property);
		PropertyResponseDto result = propertyServiceImpl.propertyDetail(propertyRequestDto);
		assertNotEquals(propertyResponseDto.getStatusCode(), result.getStatusCode());

	}
	
	@Test(expected = CustomException.class)
    public void propertyDetailemicalculatedTest() throws CustomException {
        PropertyRequestDto propertyRequestDto = new PropertyRequestDto();
        propertyRequestDto.setPanCard("123edf");
        propertyRequestDto.setInitialDeposit(10000.00);
        propertyRequestDto.setPropertyValue(2334555);

 

        PropertyResponseDto propertyResponseDto = new PropertyResponseDto();

 

        propertyResponseDto.setStatusCode(UserUtility.EMI_CALCULATED_STATUS);
        User user = new User();
        user.setEmailId("uma@hcl.com");
        user.setIncome(100000.00);
        user.setPanCard("wdf123");

 

        Property property = new Property();
        property.setEmi(1234);
        property.setPropertyId(112223);

 

        Mockito.when(propertyRepository.save(property)).thenReturn(property);
        PropertyResponseDto result = propertyServiceImpl.propertyDetail(propertyRequestDto);
        assertNotEquals(propertyResponseDto.getStatusCode(), propertyResponseDto.getStatusCode());

 

    }
}


