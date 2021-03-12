package com.org.mortgageapp.controller;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.org.mortgageapp.constant.UserUtility;
import com.org.mortgageapp.dto.ResponseDto;
import com.org.mortgageapp.dto.UserDto;
import com.org.mortgageapp.service.UserService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerTestCase {

	@InjectMocks
	UserController userController;
	@Mock
	UserService userService;

	@Test
	public void saveUserTest() {
		UserDto userDto = new UserDto();
		userDto.setUserName("asHAn");
		userDto.setDob("22/5/1996");
		userDto.setEmailId("asHan@gmail.com");
		userDto.setOccupation("It");
		userDto.setAddress("mettur");
		userDto.setPanCard("asd231");
		userDto.setIncome(10000);
		ResponseDto responseDto = new ResponseDto(UserUtility.SUCCESSSFULLY_SAVED_DATA,
				UserUtility.DATA_SAVED_CODE);
		responseDto.setMessage("User data saved successfully");
		responseDto.setStatusCode(UserUtility.DATA_SAVED_CODE);
		Mockito.when(userService.addUser(userDto)).thenReturn(responseDto);
		ResponseEntity<ResponseDto> result = userController.saveUser(userDto);
		assertEquals(HttpStatus.CREATED, result.getStatusCode());

	}

	/*
	 * @Test public void test() { fail("Not yet implemented"); }
	 */

}