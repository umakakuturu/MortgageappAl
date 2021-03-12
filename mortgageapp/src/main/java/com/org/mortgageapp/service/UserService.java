package com.org.mortgageapp.service;

import com.org.mortgageapp.dto.ResponseDto;
import com.org.mortgageapp.dto.UserDto;

public interface UserService {

	ResponseDto addUser(UserDto userDto);

}