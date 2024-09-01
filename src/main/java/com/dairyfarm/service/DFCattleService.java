package com.dairyfarm.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dairyfarm.dto.DFCattleDTO;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.enums.CattleStatus;

/**
 * @author Pavankumar - created date : Sep 28, 2021
 */
public interface DFCattleService {

	ResponseEntity<SuccessResponse> addCattleTodairyfarm(DFCattleDTO dairyfarmCattleDTO);

	ResponseEntity<SuccessResponse> updateDFCattle(DFCattleDTO dairyfarmCattleDTO);

	ResponseEntity<DFCattleDTO> fetchDFCattleDetails(long dairyfarmCattleId);

	ResponseEntity<SuccessResponse> deleteCattle(long cattleId);

	ResponseEntity<SuccessResponse> changeCattleStatus(long cattleId, CattleStatus status);

	ResponseEntity<List<DFCattleDTO>> fetchDFCattleBydairyfarm(long dairyfarmId);

}
