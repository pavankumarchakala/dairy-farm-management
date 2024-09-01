package com.dairyfarm.service;

import org.springframework.http.ResponseEntity;

import com.dairyfarm.dto.DairyfarmDTO;
import com.dairyfarm.dto.SuccessResponse;

/**
 * @author Pavankumar - created date : Sep 18, 2021
 */
public interface DFService {

	ResponseEntity<SuccessResponse> adddairyfarm(DairyfarmDTO dairyfarmDTO);

	ResponseEntity<SuccessResponse> updatedairyfarm(DairyfarmDTO dairyfarmDTO);

	ResponseEntity<DairyfarmDTO> fetchdairyfarmDetails(long dairyfarmId);

	ResponseEntity<SuccessResponse> adddairyfarmToUser(DairyfarmDTO dairyfarmDTO, long userId);

	ResponseEntity<SuccessResponse> deletedairyfarm(long dairyfarmId);

}
