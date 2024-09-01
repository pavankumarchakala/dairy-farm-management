package com.dairyfarm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dairyfarm.constant.UserRegistrationConstants;
import com.dairyfarm.dao.DFRepository;
import com.dairyfarm.dao.DFUserRepository;
import com.dairyfarm.dao.UserRepository;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.dto.UserDTO;
import com.dairyfarm.entity.DFUser;
import com.dairyfarm.entity.Dairyfarm;
import com.dairyfarm.entity.User;
import com.dairyfarm.enums.Status;
import com.dairyfarm.exceptions.DairyfarmmanagementException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Pavankumar - created date : Sep 13, 2021
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final DFUserRepository dairyfarmUserRepository;

	private final DFRepository dairyfarmRepository;

	private final ModelMapper modelMapper;

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> addUser(UserDTO userDTO, long dairyfarmId)
			throws DairyfarmmanagementException {
		User user = userRepository.save(modelMapper.map(userDTO, User.class));

		if (0 != dairyfarmId) {
			Dairyfarm dairyfarm = dairyfarmRepository.getById(dairyfarmId);
			dairyfarmUserRepository
					.save(DFUser.builder().dairyfarm(dairyfarm).status(Status.ACTIVE).user(user).build());
		}

		return new ResponseEntity<SuccessResponse>(SuccessResponse.builder().savedItemId(user.getId())
				.message("User Successfully added.").status(HttpStatus.CREATED).build(), HttpStatus.CREATED);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> updateUser(UserDTO userDTO) throws DairyfarmmanagementException {
		User user = userRepository.getById(userDTO.getId());
		modelMapper.map(userDTO, user);
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().message("User Successfully updated.").status(HttpStatus.OK).build(),
				HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<UserDTO> fetchUserDetails(long userId) throws DairyfarmmanagementException {
		User user = userRepository.getById(userId);
		return new ResponseEntity<UserDTO>(modelMapper.map(user, UserDTO.class), HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> deleteUser(long userId) throws DairyfarmmanagementException {
		Optional<User> optionalUser = userRepository.findById(userId);

		if (!optionalUser.isPresent()) {
			log.error("User doesn't exist with the provided ID.");
			throw new DairyfarmmanagementException("User doesn't exist with the provided Id.", HttpStatus.NOT_FOUND);
		}

		optionalUser.get().setStatus(Status.INACTIVE);
		List<DFUser> dairyfarmUsers = dairyfarmUserRepository.findAllByUserId(userId);
		dairyfarmUsers.stream().forEach(dairyfarmUser -> dairyfarmUser.setStatus(Status.INACTIVE));
		return new ResponseEntity<SuccessResponse>(SuccessResponse.builder()
				.message("dairyfarm is deleted for the  Successfully updated.").status(HttpStatus.OK).build(),
				HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> activateUser(String mobile) {
		User user = userRepository.findByMobile(mobile);
		if (null == user)
			throw new DairyfarmmanagementException("User doesn't exist with the provided mobile number.",
					HttpStatus.NOT_FOUND);
		user.setStatus(Status.ACTIVE);
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().message("User has be activated.").status(HttpStatus.OK).build(),
				HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> checkUserStatus(String mobile) {
		User user = userRepository.findByMobile(mobile);
		if (null == user)
			throw new DairyfarmmanagementException("User doesn't exist with the provided mobile number.",
					HttpStatus.NOT_FOUND);
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().message(user.getStatus().getDislayValue()).status(HttpStatus.OK).build(),
				HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> sendOTP(String mobile) {
		RestTemplate restTemplate = new RestTemplate();
		String url = UserRegistrationConstants.SEND_OTP_URL
				+ "?template_id={template_id}&mobile={mobile}&authkey={authkey}";
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("template_id", UserRegistrationConstants.TEMPLATE_ID);
		uriVariables.put("mobile", mobile);
		uriVariables.put("authkey", UserRegistrationConstants.AUTH_KEY);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class, uriVariables);
		if (!HttpStatus.OK.equals(response.getStatusCode()))
			throw new DairyfarmmanagementException(response.getBody());
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().message("OTP Sent Successfully.").status(response.getStatusCode()).build(),
				response.getStatusCode());
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> resendOTP(String mobile) {
		RestTemplate restTemplate = new RestTemplate();
		String url = UserRegistrationConstants.SEND_OTP_URL + "?retrytype=text&mobile={mobile}&authkey={authkey}";
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("template_id", UserRegistrationConstants.TEMPLATE_ID);
		uriVariables.put("mobile", mobile);
		uriVariables.put("authkey", UserRegistrationConstants.AUTH_KEY);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class, uriVariables);
		if (!HttpStatus.OK.equals(response.getStatusCode()))
			throw new DairyfarmmanagementException(response.getBody());
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().message("OTP Sent Successfully.").status(response.getStatusCode()).build(),
				response.getStatusCode());
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> verifyOTP(String mobile, String otp) {
		RestTemplate restTemplate = new RestTemplate();
		String url = UserRegistrationConstants.VERIFY_OTP_URL + "?authkey={authkey}&mobile={mobile}&otp={otp}";
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("otp", otp);
		uriVariables.put("mobile", mobile);
		uriVariables.put("authkey", UserRegistrationConstants.AUTH_KEY);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class, uriVariables);
		if (!HttpStatus.OK.equals(response.getStatusCode()))
			throw new DairyfarmmanagementException(response.getBody());
		return new ResponseEntity<SuccessResponse>(SuccessResponse.builder().message("OTP Verified Successfully.")
				.status(response.getStatusCode()).build(), response.getStatusCode());
	}

	// This method is for Test purpose
	@Override
	public List<Long> getAllUserIds() {
		return userRepository.findAll().stream().map(user -> user.getId()).collect(Collectors.toList());
	}

}
