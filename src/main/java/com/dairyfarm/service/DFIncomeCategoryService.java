package com.dairyfarm.service;

import org.springframework.http.ResponseEntity;

import com.dairyfarm.dto.DFIncomeCategoryDTO;
import com.dairyfarm.dto.SuccessResponse;

/**
 * @author Pavankumar - created date : Oct 15, 2021
 */
public interface DFIncomeCategoryService {

	ResponseEntity<SuccessResponse> addDFIncomeCategory(DFIncomeCategoryDTO dairyfarmIncomeCategoryDTO);

	ResponseEntity<SuccessResponse> updateDFIncomeCategory(DFIncomeCategoryDTO dairyfarmIncomeCategoryDTO);

	ResponseEntity<DFIncomeCategoryDTO> fetchDFIncomeCategoryDetails(long categoryId);

	ResponseEntity<SuccessResponse> deleteDFIncomeCategory(long categoryId);

}
