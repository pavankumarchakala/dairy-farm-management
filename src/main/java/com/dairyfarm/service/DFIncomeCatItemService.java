package com.dairyfarm.service;

import org.springframework.http.ResponseEntity;

import com.dairyfarm.dto.DFIncomeCategoryItemDTO;
import com.dairyfarm.dto.SuccessResponse;

/**
 * @author Pavankumar - created date : Oct 15, 2021
 */
public interface DFIncomeCatItemService {

	ResponseEntity<SuccessResponse> addDFIncomeCatItem(DFIncomeCategoryItemDTO dairyfarmIncomeCatItemDTO);

	ResponseEntity<SuccessResponse> updateDFIncomeCatItem(DFIncomeCategoryItemDTO dairyfarmIncomeCatItemDTO);

	ResponseEntity<DFIncomeCategoryItemDTO> fetchDFIncomeCatItemDetails(long itemId);

	ResponseEntity<SuccessResponse> deleteDFIncomeCatItem(long itemId);

}
