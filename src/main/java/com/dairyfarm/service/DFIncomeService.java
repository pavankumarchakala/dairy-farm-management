package com.dairyfarm.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.dairyfarm.dto.DFIncomeDTO;
import com.dairyfarm.dto.PaginatedResponse;
import com.dairyfarm.dto.SuccessResponse;

/**
 * @author Pavankumar - created date : Oct 14, 2021
 */
public interface DFIncomeService {

	ResponseEntity<SuccessResponse> addDFIncome(DFIncomeDTO dairyfarmIncomeDTO);

	ResponseEntity<SuccessResponse> updateDFIncome(DFIncomeDTO dairyfarmIncomeDTO);

	ResponseEntity<SuccessResponse> deleteDFIncome(long dairyfarmIncomeId);

	ResponseEntity<DFIncomeDTO> fetchDFIncomeDetails(long incomeId);

	ResponseEntity<PaginatedResponse<DFIncomeDTO>> fetchAllDFIncomesBydairyfarm(Pageable pageable, long dairyfarmId);

}
