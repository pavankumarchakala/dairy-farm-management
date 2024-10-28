package com.dairyfarm.controller;

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

import com.dairyfarm.dto.DFExpenseDTO;
import com.dairyfarm.dto.PaginatedResponse;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.service.DFExpenseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Oct 13, 2021
 */
@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class DFExpenseController {

	private final DFExpenseService dairyfarmExpenseService;

	@Operation(summary = "Add Dairyfarm Expense", description = "Add Dairyfarm Expense details")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), responseCode = "201", description = "Dairyfarm Expense record created successfully") })
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> addDFExpense(@RequestBody DFExpenseDTO dairyfarmExpenseDTO) {
		return dairyfarmExpenseService.addDFExpense(dairyfarmExpenseDTO);
	}

	@Operation(summary = "Update Dairyfarm Expense details", description = "Update Dairyfarm Expense details")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), responseCode = "200", description = "Dairyfarm Expense record updated successfully") })
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> updateDFExpense(@RequestBody DFExpenseDTO dairyfarmExpenseDTO) {
		return dairyfarmExpenseService.updateDFExpense(dairyfarmExpenseDTO);
	}

	@Operation(summary = "Fetch Dairyfarm Expense", description = "Fetch Dairyfarm Expense details")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), responseCode = "200", description = "Fetch Dairyfarm Expense details") })
	@PutMapping(value = "/{expenseId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DFExpenseDTO> fetchDFExpenseDetails(@PathVariable("expenseId") long expenseId) {
		return dairyfarmExpenseService.fetchDFExpenseDetails(expenseId);
	}

	@Operation(summary = "Delete Dairyfarm Expense record", description = "Delete Dairyfarm Expense record details")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), responseCode = "201", description = "Dairyfarm Expense record created successfully") })
	@DeleteMapping(value = "/delete/{expenseId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> updateDFExpense(@PathVariable("expenseId") long expenseId) {
		return dairyfarmExpenseService.deleteDFExpense(expenseId);
	}

	@Operation(summary = "Fetch Dairyfarm Expense", description = "Fetch Dairyfarm Expense records")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), responseCode = "200", description = "Fetch Dairyfarm Expense records") })
	@GetMapping(value = "/all/{dairyfarmId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaginatedResponse<DFExpenseDTO>> fetchDFExpenses(
			@PageableDefault(page = 0, size = 10, sort = "paidDate", direction = Direction.DESC) Pageable pageable,
			@PathVariable("dairyfarmId") long dairyfarmId) {
		return dairyfarmExpenseService.fetchAllDFExpensesBydairyfarm(pageable, dairyfarmId);
	}

}
