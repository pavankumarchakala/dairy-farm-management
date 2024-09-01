package com.dairyfarm.controller;

import javax.websocket.server.PathParam;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dairyfarm.dto.DFIncomeDTO;
import com.dairyfarm.dto.PaginatedResponse;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.service.DFIncomeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Oct 13, 2021
 */
@RestController
@RequestMapping("/income")
@RequiredArgsConstructor
public class DFIncomeController {

	private final DFIncomeService dairyfarmIncomeService;

	@Operation(summary = "Add Dairyfarm Income", description = "Add Dairyfarm Income details")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "201",
			description = "Dairyfarm Income record created successfully") })
	@PostMapping(
			value = "/add",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> addDFIncome(@RequestBody DFIncomeDTO dairyfarmIncomeDTO) {
		return dairyfarmIncomeService.addDFIncome(dairyfarmIncomeDTO);
	}

	@Operation(summary = "Update Dairyfarm Income details", description = "Update Dairyfarm Income details")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "Dairyfarm Income record updated successfully") })
	@PutMapping(
			value = "/update",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> updateDFIncome(@RequestBody DFIncomeDTO dairyfarmIncomeDTO) {
		return dairyfarmIncomeService.updateDFIncome(dairyfarmIncomeDTO);
	}

	@Operation(summary = "Fetch Dairyfarm Income", description = "Fetch Dairyfarm Income details")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "Fetch Dairyfarm Income details") })
	@GetMapping(value = "/{incomeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DFIncomeDTO> fetchDFIncomeDetails(@PathVariable("incomeId") long incomeId) {
		return dairyfarmIncomeService.fetchDFIncomeDetails(incomeId);
	}

	@Operation(summary = "Delete Dairyfarm", description = "Delete Dairyfarm Income record details")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "201",
			description = "Dairyfarm created successfully") })
	@DeleteMapping(value = "/delete/{incomeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> deleteDFIncome(@PathParam("incomeId") long incomeId) {
		return dairyfarmIncomeService.deleteDFIncome(incomeId);
	}

	@Operation(summary = "Fetch Dairyfarm Income", description = "Fetch Dairyfarm Income records")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "Fetch Dairyfarm Income records") })
	@GetMapping(value = "/all/{dairyfarmId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaginatedResponse<DFIncomeDTO>> fetchDFIncomes(
			@PageableDefault(page = 0, size = 10, sort = "receivedDate", direction = Direction.DESC) Pageable pageable,
			@PathVariable("dairyfarmId") long dairyfarmId) {
		return dairyfarmIncomeService.fetchAllDFIncomesBydairyfarm(pageable, dairyfarmId);
	}

}
