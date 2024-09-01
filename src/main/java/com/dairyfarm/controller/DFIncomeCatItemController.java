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

import com.dairyfarm.dto.DFIncomeCategoryItemDTO;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.service.DFIncomeCatItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Oct 15, 2021
 */
@RestController
@RequestMapping("/incomeitem")
@RequiredArgsConstructor
public class DFIncomeCatItemController {

	private final DFIncomeCatItemService dairyfarmIncomeCatItemService;

	@Operation(
			summary = "Add Dairyfarm Income Category Item Item",
			description = "Add Dairyfarm Income Category Item details")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "201",
			description = "Dairyfarm Income Category Item record created successfully") })
	@PostMapping(
			value = "/add",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> addDFIncomeCategoryItem(
			@RequestBody DFIncomeCategoryItemDTO dairyfarmIncomeCategoryItemDTO) {
		return dairyfarmIncomeCatItemService.addDFIncomeCatItem(dairyfarmIncomeCategoryItemDTO);
	}

	@Operation(
			summary = "Update Dairyfarm Income Category Item details",
			description = "Update Dairyfarm Income Category Item details")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "Dairyfarm Income Category Item record updated successfully") })
	@PutMapping(
			value = "/update",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> updateDFIncomeCategoryItem(
			@RequestBody DFIncomeCategoryItemDTO dairyfarmIncomeCategoryItemDTO) {
		return dairyfarmIncomeCatItemService.updateDFIncomeCatItem(dairyfarmIncomeCategoryItemDTO);
	}

	@Operation(
			summary = "Fetch Dairyfarm Income Category Item Item",
			description = "Fetch Dairyfarm Income Category Item details")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "Fetch Dairyfarm Income Category Item details") })
	@PutMapping(
			value = "/{itemId}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DFIncomeCategoryItemDTO> fetchDFItemDetails(@PathVariable("itemId") long itemId) {
		return dairyfarmIncomeCatItemService.fetchDFIncomeCatItemDetails(itemId);
	}

	@Operation(
			summary = "Delete Dairyfarm Income Category Item Item",
			description = "Delete Dairyfarm Income Category Item record details")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "201",
			description = "Dairyfarm Income Category Item created successfully") })
	@DeleteMapping(value = "/delete/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> updateDFIncomeCatItem(@PathParam("itemId") long itemId) {
		return dairyfarmIncomeCatItemService.deleteDFIncomeCatItem(itemId);
	}

}
