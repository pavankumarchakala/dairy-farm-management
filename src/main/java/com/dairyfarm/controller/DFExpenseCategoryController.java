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

import com.dairyfarm.dto.DFExpenseCategoryDTO;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.service.DFExpenseCategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Oct 16, 2021
 */
@RestController
@RequestMapping("/expensecategory")
@RequiredArgsConstructor
public class DFExpenseCategoryController {

	private final DFExpenseCategoryService dairyfarmExpenseCategoryService;

	@Operation(summary = "Add Dairyfarm Expense Category", description = "Add Dairyfarm Expense Category details")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), responseCode = "201", description = "Dairyfarm Expense Category record created successfully") })
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> addDFExpenseCategory(
			@RequestBody DFExpenseCategoryDTO dairyfarmExpenseCategoryDTO) {
		return dairyfarmExpenseCategoryService.addDFExpenseCategory(dairyfarmExpenseCategoryDTO);
	}

	@Operation(summary = "Update Dairyfarm Expense Category details", description = "Update Dairyfarm Expense Category details")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), responseCode = "200", description = "Dairyfarm Expense Category record updated successfully") })
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> updateDFExpense(
			@RequestBody DFExpenseCategoryDTO dairyfarmExpenseCategoryDTO) {
		return dairyfarmExpenseCategoryService.updateDFExpenseCategory(dairyfarmExpenseCategoryDTO);
	}

	@Operation(summary = "Fetch Dairyfarm Expense Category", description = "Fetch Dairyfarm Expense Category details")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), responseCode = "200", description = "Fetch Dairyfarm Expense Category details") })
	@PutMapping(value = "/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DFExpenseCategoryDTO> fetchDFExpenseCategoryDetails(
			@PathVariable("categoryId") long categoryId) {
		return dairyfarmExpenseCategoryService.fetchDFExpenseCategoryDetails(categoryId);
	}

	@Operation(summary = "Delete Dairyfarm Expense Category", description = "Delete Dairyfarm Expense Category record details")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), responseCode = "201", description = "Dairyfarm Expense Category created successfully") })
	@DeleteMapping(value = "/delete/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> updateDFExpenseCategory(@PathVariable("categoryId") long categoryId) {
		return dairyfarmExpenseCategoryService.deleteDFExpenseCategory(categoryId);
	}

}
