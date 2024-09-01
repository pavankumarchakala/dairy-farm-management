package com.dairyfarm.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dairyfarm.dao.DFUserRepository;
import com.dairyfarm.dto.DFUserDTO;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.entity.DFUser;

import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Oct 2, 2021
 */
@Service
@RequiredArgsConstructor
public class DFUserServiceImpl implements DFUserService {

	private final DFUserRepository dairyfarmUserRepository;

	private final ModelMapper modelMapper;

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> adddairyfarmUser(DFUserDTO dairyfarmUserDTO) {

		if (null != dairyfarmUserDTO.getUser() && 0 != dairyfarmUserDTO.getUser().getId()) {
		}

		DFUser dairyfarmUser = modelMapper.map(dairyfarmUserDTO, DFUser.class);
		dairyfarmUserRepository.save(dairyfarmUser);
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().savedItemId(dairyfarmUser.getId())
						.message("User is successfully associated to a dairyfarm.").status(HttpStatus.CREATED).build(),
				HttpStatus.CREATED);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> updatedairyfarmUser(DFUserDTO dairyfarmUserDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public ResponseEntity<DFUserDTO> fetchdairyfarmUserDetails(long dairyfarmUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> deassociateAlldairyfarmsForAnUser(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
