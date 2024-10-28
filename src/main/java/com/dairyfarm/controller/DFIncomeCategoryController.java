package com.dairyfarm.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dairyfarm.dto.DFIncomeCategoryDTO;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.service.DFIncomeCategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Oct 15, 2021
 */
@RestController
@RequestMapping("/incomecategory")
@RequiredArgsConstructor
public class DFIncomeCategoryController {

	private final DFIncomeCategoryService dairyfarmIncomeCategoryService;

	@Operation(summary = "Add Dairyfarm Income Category", description = "Add Dairyfarm Income Category details")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), responseCode = "201", description = "Dairyfarm Income Category record created successfully") })
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> addDFIncomeCategory(
			@RequestBody DFIncomeCategoryDTO dairyfarmIncomeCategoryDTO) {
		return dairyfarmIncomeCategoryService.addDFIncomeCategory(dairyfarmIncomeCategoryDTO);
	}

	@Operation(summary = "Update Dairyfarm Income Category details", description = "Update Dairyfarm Income Category details")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), responseCode = "200", description = "Dairyfarm Income Category record updated successfully") })
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> updateDFIncomeCategory(
			@RequestBody DFIncomeCategoryDTO dairyfarmIncomeCategoryDTO) {
		return dairyfarmIncomeCategoryService.updateDFIncomeCategory(dairyfarmIncomeCategoryDTO);
	}

	@Operation(summary = "Fetch Dairyfarm Income Category", description = "Fetch Dairyfarm Income Category details")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), responseCode = "200", description = "Fetch Dairyfarm Income Category details") })
	@PutMapping(value = "/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DFIncomeCategoryDTO> fetchDFIncomeCategoryDetails(
			@PathVariable("categoryId") long categoryId) {
		return dairyfarmIncomeCategoryService.fetchDFIncomeCategoryDetails(categoryId);
	}

	@Operation(summary = "Delete Dairyfarm Income Category", description = "Delete Dairyfarm Income Category record details")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), responseCode = "201", description = "Dairyfarm Income Category created successfully") })
	@DeleteMapping(value = "/delete/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> deleteDFIncomeCategory(@PathVariable("categoryId") long categoryId) {
		return dairyfarmIncomeCategoryService.deleteDFIncomeCategory(categoryId);
	}

}
