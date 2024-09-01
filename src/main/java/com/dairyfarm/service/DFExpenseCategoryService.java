package com.dairyfarm.service;

import org.springframework.http.ResponseEntity;

import com.dairyfarm.dto.DFExpenseCategoryDTO;
import com.dairyfarm.dto.SuccessResponse;

/**
 * @author Pavankumar - created date : Oct 15, 2021
 */
public interface DFExpenseCategoryService {

	ResponseEntity<SuccessResponse> addDFExpenseCategory(DFExpenseCategoryDTO dairyfarmExpenseCategoryDTO);

	ResponseEntity<SuccessResponse> updateDFExpenseCategory(DFExpenseCategoryDTO dairyfarmExpenseCategoryDTO);

	ResponseEntity<DFExpenseCategoryDTO> fetchDFExpenseCategoryDetails(long categoryId);

	ResponseEntity<SuccessResponse> deleteDFExpenseCategory(long categoryId);

}
