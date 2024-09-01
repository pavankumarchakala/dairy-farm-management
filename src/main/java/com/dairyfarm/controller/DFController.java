package com.dairyfarm.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dairyfarm.dto.DairyfarmDTO;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.service.DFService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Sep 13, 2021
 */
@RestController
@RequestMapping("/dairyfarm")
@RequiredArgsConstructor
public class DFController {

	private final DFService dairyfarmService;

	@Operation(summary = "Add Dairyfarm", description = "Add Dairyfarm with it's User details")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), responseCode = "201", description = "Dairyfarm created successfully") })
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Hidden
	public ResponseEntity<SuccessResponse> adddairyfarm(@RequestBody DairyfarmDTO dairyfarmDTO) {
		return dairyfarmService.adddairyfarm(dairyfarmDTO);
	}
	/*
	 * @Operation(summary = "Add Dairyfarm to User", description =
	 * "Add Dairyfarm to existing User")
	 * 
	 * @ApiResponses({ @ApiResponse( content = @Content(mediaType =
	 * MediaType.APPLICATION_JSON_VALUE), responseCode = "201", description =
	 * "Dairyfarm associated to User successfully") })
	 * 
	 * @PostMapping( value = "/add/{userId}", consumes =
	 * MediaType.APPLICATION_JSON_VALUE, produces =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<SuccessResponse>
	 * adddairyfarm(@RequestBody dairyfarmDTO dairyfarmDTO,
	 * 
	 * @PathVariable("userId") long userId) { return
	 * dairyfarmService.adddairyfarmToUser(dairyfarmDTO, userId); }
	 */

	@Operation(summary = "Update Dairyfarm", description = "Update Dairyfarm")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), responseCode = "200", description = "Dairyfarm created successfully") })
	@PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> updatedairyfarm(@RequestBody DairyfarmDTO dairyfarmDTO) {
		return dairyfarmService.updatedairyfarm(dairyfarmDTO);
	}

	@Operation(summary = "Fetch Dairyfarm details", description = "Fetch Dairyfarm details")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), responseCode = "200") })
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DairyfarmDTO> getdairyfarmDetailsById(@PathVariable("id") long dairyfarmId) {
		return dairyfarmService.fetchdairyfarmDetails(dairyfarmId);
	}

	@Operation(summary = "Delete Dairyfarm", description = "Delete Dairyfarm")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), responseCode = "200", description = "User de-associated with Dairyfarm(s) successfully") })
	@DeleteMapping(value = "/delete/{dairyfarmId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> deleteUser(@PathVariable("dairyfarmId") long dairyfarmId) {
		return dairyfarmService.deletedairyfarm(dairyfarmId);
	}

}
