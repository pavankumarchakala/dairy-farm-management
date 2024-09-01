package com.dairyfarm.service;

import org.springframework.http.ResponseEntity;

import com.dairyfarm.dto.DFUserDTO;
import com.dairyfarm.dto.SuccessResponse;

/**
 * @author Pavankumar - created date : Sep 13, 2021
 */
public interface DFUserService {

	ResponseEntity<SuccessResponse> adddairyfarmUser(DFUserDTO dairyfarmUserDTO);

	ResponseEntity<SuccessResponse> updatedairyfarmUser(DFUserDTO dairyfarmUserDTO);

	ResponseEntity<DFUserDTO> fetchdairyfarmUserDetails(long dairyfarmUserId);

	ResponseEntity<SuccessResponse> deassociateAlldairyfarmsForAnUser(long userId);

}
