package com.dairyfarm.controller;

import javax.websocket.server.PathParam;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dairyfarm.dto.DFExpenseCategoryItemDTO;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.service.DFExpenseCatItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Oct 15, 2021
 */
@RestController
@RequestMapping("/expenseitem")
@RequiredArgsConstructor
public class DFExpenseCatItemController {

	private final DFExpenseCatItemService dairyfarmExpenseCatItemService;

	@Operation(
			summary = "Add Dairyfarm Expense Category Item Item",
			description = "Add Dairyfarm Expense Category Item details")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "201",
			description = "Dairyfarm Expense Category Item record created successfully") })
	@PostMapping(
			value = "/add",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> addDFExpenseCatItem(
			@RequestBody DFExpenseCategoryItemDTO dairyfarmExpenseCategoryItemDTO) {
		return dairyfarmExpenseCatItemService.addDFExpenseCatItem(dairyfarmExpenseCategoryItemDTO);
	}

	@Operation(
			summary = "Update Dairyfarm Expense Category Item details",
			description = "Update Dairyfarm Expense Category Item details")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "Dairyfarm Expense Category Item record updated successfully") })
	@PutMapping(
			value = "/update",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> updateDFExpenseCatItem(
			@RequestBody DFExpenseCategoryItemDTO dairyfarmExpenseCategoryItemDTO) {
		return dairyfarmExpenseCatItemService.updateDFExpenseCatItem(dairyfarmExpenseCategoryItemDTO);
	}

	@Operation(
			summary = "Fetch Dairyfarm Expense Category Item Item",
			description = "Fetch Dairyfarm Expense Category Item details")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "Fetch Dairyfarm Expense Category Item details") })
	@PutMapping(
			value = "/{itemId}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DFExpenseCategoryItemDTO> fetchDFExpenseCatItemDetails(@PathVariable("itemId") long itemId) {
		return dairyfarmExpenseCatItemService.fetchDFExpenseCatItemDetails(itemId);
	}

	@Operation(
			summary = "Delete Dairyfarm Expense Category Item Item",
			description = "Delete Dairyfarm Expense Category Item record details")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "201",
			description = "Dairyfarm Expense Category Item created successfully") })
	@DeleteMapping(value = "/delete/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> deleteDFExpenseCatItem(@PathParam("itemId") long itemId) {
		return dairyfarmExpenseCatItemService.deleteDFExpenseCatItem(itemId);
	}

}
