package com.dairyfarm.service;

import org.springframework.http.ResponseEntity;

import com.dairyfarm.dto.DFExpenseCategoryItemDTO;
import com.dairyfarm.dto.SuccessResponse;

/**
 * @author Pavankumar - created date : Oct 15, 2021
 */
public interface DFExpenseCatItemService {

	ResponseEntity<SuccessResponse> addDFExpenseCatItem(DFExpenseCategoryItemDTO dairyfarmExpenseCatItemDTO);

	ResponseEntity<SuccessResponse> updateDFExpenseCatItem(DFExpenseCategoryItemDTO dairyfarmExpenseCatItemDTO);

	ResponseEntity<DFExpenseCategoryItemDTO> fetchDFExpenseCatItemDetails(long itemId);

	ResponseEntity<SuccessResponse> deleteDFExpenseCatItem(long itemId);

}
