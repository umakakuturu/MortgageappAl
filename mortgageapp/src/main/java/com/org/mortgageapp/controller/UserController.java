package com.org.mortgageapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.mortgageapp.dto.ResponseDto;
import com.org.mortgageapp.dto.UserDto;
import com.org.mortgageapp.service.UserService;

/**
 * 
 * @author asHan
 * @since 2020-03-30
 *
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * This method is used to save user profile
	 * 
	 * 
	 * @param LuserDto -contains the request params address
	 *                 ,dob,userName,occupation,income,panCard and emailId
	 *                 ResponseDto- contains success or failure message as a
	 *                 response
	 * @return ResponseEntity Object along with status code and message
	 */

	@PostMapping("/users")
	public ResponseEntity<ResponseDto> saveUser(@Valid @RequestBody UserDto userDto) {
		ResponseDto responseDto = userService.addUser(userDto);

		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

	}

}