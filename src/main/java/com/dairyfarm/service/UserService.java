package com.dairyfarm.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.dto.UserDTO;

/**
 * @author Pavankumar - created date : Sep 13, 2021
 */
public interface UserService {

	ResponseEntity<SuccessResponse> addUser(UserDTO userDTO, long dairyfarmId);

	ResponseEntity<SuccessResponse> updateUser(UserDTO userDTO);

	ResponseEntity<UserDTO> fetchUserDetails(long userId);

	ResponseEntity<SuccessResponse> deleteUser(long userId);

	ResponseEntity<SuccessResponse> sendOTP(String mobile);

	ResponseEntity<SuccessResponse> resendOTP(String mobile);

	ResponseEntity<SuccessResponse> verifyOTP(String mobile, String otp);

	ResponseEntity<SuccessResponse> activateUser(String mobile);

	ResponseEntity<SuccessResponse> checkUserStatus(String mobile);

	// This method is for Test purpose
	List<Long> getAllUserIds();

}
