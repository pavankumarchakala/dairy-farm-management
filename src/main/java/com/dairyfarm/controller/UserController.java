package com.dairyfarm.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.dto.UserDTO;
import com.dairyfarm.enums.UserRole;
import com.dairyfarm.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Sep 13, 2021
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@Operation(summary = "Add Owner", description = "Add Owner details with address details")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "201",
			description = "Owner created successfully") })
	@PostMapping(
			value = "/add",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> addOwner(@RequestBody UserDTO userDTO) {
		userDTO.setRole(UserRole.OWNER);
		return userService.addUser(userDTO, 0);
	}

	@Operation(summary = "Add Coowner", description = "Add Coowner with address details for a dairyfarm")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "201",
			description = "Coowner created successfully") })
	@PostMapping(
			value = "/add/{dairyfarmId}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> addCoOwner(@RequestBody UserDTO userDTO,
			@PathVariable("dairyfarmId") long dairyfarmId) {
		userDTO.setRole(UserRole.COOWNER);
		return userService.addUser(userDTO, dairyfarmId);
	}

	@Operation(summary = "Update User", description = "Update User details")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "User created successfully") })
	@PutMapping(
			value = "/update",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> updateUser(@RequestBody UserDTO userDTO) {
		return userService.updateUser(userDTO);
	}

	@Operation(summary = "Fetch User Details", description = "Fetch User Details")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "User Details") })
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getUserDetailsById(@PathVariable("id") long userId) {
		return userService.fetchUserDetails(userId);
	}

	@Operation(summary = "Delete User", description = "Delete User")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "User de-associated with Dairyfarm(s) successfully") })
	@DeleteMapping(value = "/delete/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> deleteUser(@PathVariable("userId") long userId) {
		return userService.deleteUser(userId);
	}

	@Operation(summary = "Activate User", description = "Activate the Inactive User")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "Activate the Inactive User") })
	@PutMapping(value = "/activate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> activateUser(@RequestParam("mobile") String mobile) {
		return userService.activateUser(mobile);
	}

	@Operation(summary = "Check User Status", description = "Check User Status")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "Check User Status") })
	@GetMapping(value = "/checkstatus", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> checkUserStatus(@RequestParam("mobile") String mobile) {
		return userService.checkUserStatus(mobile);
	}

	@Operation(summary = "Send OTP", description = "Send OTP for Authentication/Registration")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "OTP Sent Successfully") })
	@GetMapping(value = "/sendOTP", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> sendOTP(@RequestParam("mobile") String mobile) {
		return userService.sendOTP(mobile);
	}

	@Operation(summary = "ReSend OTP", description = "ReSend OTP for Authentication/Registration")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "OTP Sent Successfully") })
	@GetMapping(value = "/resendOTP", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> reSendOTP(@RequestParam("mobile") String mobile) {
		return userService.resendOTP(mobile);
	}

	@Operation(summary = "Verify OTP", description = "Verify OTP for Authentication/Registration")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "OTP Verified Successfully") })
	@GetMapping(value = "/verifyOTP", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> verifyOTP(@RequestParam("mobile") String mobile,
			@RequestParam("otp") String otp) {
		return userService.verifyOTP(mobile, otp);
	}

}
