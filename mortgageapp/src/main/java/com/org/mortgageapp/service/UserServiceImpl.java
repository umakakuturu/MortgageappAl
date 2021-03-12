package com.org.mortgageapp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.mortgageapp.constant.UserUtility;
import com.org.mortgageapp.dto.ResponseDto;
import com.org.mortgageapp.dto.UserDto;
import com.org.mortgageapp.entity.User;
import com.org.mortgageapp.repository.UserRepository;

/**
 * @author ashan
 * @since 2020-03-30
 *
 */

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

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

	@Override
	public ResponseDto addUser(UserDto userDto) {
		User user = new User();

		BeanUtils.copyProperties(userDto, user);
		User userCheck = userRepository.findByPanCard(user.getPanCard());
		if (userCheck != null) {
			return new ResponseDto(UserUtility.PANCARD_FOUND, UserUtility.PANCARD_ERROR_CODE);
		}

		userRepository.save(user);

		return new ResponseDto(UserUtility.SUCCESSSFULLY_SAVED_DATA, UserUtility.DATA_SAVED_CODE);

	}

}