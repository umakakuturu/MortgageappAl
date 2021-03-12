package com.org.mortgageapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.org.mortgageapp.constant.UserUtility;
import com.org.mortgageapp.dto.ResponseDto;
import com.org.mortgageapp.dto.UserDto;
import com.org.mortgageapp.entity.User;
import com.org.mortgageapp.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceImplTestCase {

	@InjectMocks
	UserServiceImpl userServiceImpl;
	@Mock
	UserRepository userRepository;

	ResponseDto response = new ResponseDto("User data saved", HttpStatus.CREATED.value());
	ResponseDto response1 = new ResponseDto("User already exists", HttpStatus.CREATED.value());
	User user = new User();

	@Before
	public void setup() {
		user.setUserName("asHAn");
		user.setDob("22/5/1996");
		user.setEmailId("asHan@gmail.com");
		user.setOccupation("It");
		user.setAddress("mettur");
		user.setPanCard("asd231");
		user.setIncome(10000);

	}

	@Test
	public void testAddUser() {
		UserDto userDto = new UserDto();

		Mockito.when(userRepository.save(user)).thenReturn(user);
		ResponseDto responseDto = userServiceImpl.addUser(userDto);
		assertEquals(607, responseDto.getStatusCode());
	}

	@Test
	public void testAddUser1() {
		UserDto userDto = new UserDto();

		userDto.setPanCard("DVJ231");
		ResponseDto responseDto = new ResponseDto(UserUtility.PANCARD_FOUND, UserUtility.PANCARD_ERROR_CODE);
		responseDto.setMessage("already user exists");
		responseDto.setStatusCode(UserUtility.PANCARD_ERROR_CODE);

		User user = new User();
		user.setPanCard("DVJ231");
		Mockito.when(userRepository.findByPanCard("DVJ231")).thenReturn(user);
		ResponseDto result = userServiceImpl.addUser(userDto);
		assertEquals(responseDto.getStatusCode(), result.getStatusCode());
	}

	/*
	 * @Test public void test() { fail("Not yet implemented"); }
	 */
}
