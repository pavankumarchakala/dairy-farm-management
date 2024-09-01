package com.dairyfarm.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.dairyfarm.dto.DFExpenseDTO;
import com.dairyfarm.dto.PaginatedResponse;
import com.dairyfarm.dto.SuccessResponse;

/**
 * @author Pavankumar - created date : Oct 14, 2021
 */
public interface DFExpenseService {

	ResponseEntity<SuccessResponse> addDFExpense(DFExpenseDTO dairyfarmExpenseDTO);

	ResponseEntity<SuccessResponse> updateDFExpense(DFExpenseDTO dairyfarmExpenseDTO);

	ResponseEntity<SuccessResponse> deleteDFExpense(long expenseId);

	ResponseEntity<DFExpenseDTO> fetchDFExpenseDetails(long expenseId);

	ResponseEntity<PaginatedResponse<DFExpenseDTO>> fetchAllDFExpensesBydairyfarm(Pageable pageable, long dairyfarmId);

}
