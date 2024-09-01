package com.dairyfarm.controller;

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

import com.dairyfarm.dto.DFCattleDTO;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.enums.CattleStatus;
import com.dairyfarm.service.DFCattleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Sep 28, 2021
 */
@RestController
@RequestMapping("/cattle")
@RequiredArgsConstructor
public class DFCattleController {

	private final DFCattleService dairyfarmCattleService;

	@Operation(summary = "Add Cattle to Dairyfarm", description = "Add Cattle to Dairyfarm")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "201",
			description = "Cattle added to Dairyfarm successfully") })
	@PostMapping(
			value = "/add",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> adddairyfarm(@RequestBody DFCattleDTO dairyfarmCattleDTO) {
		return dairyfarmCattleService.addCattleTodairyfarm(dairyfarmCattleDTO);
	}

	@Operation(summary = "Update Cattle details", description = "Update Cattle details of a Dairyfarm")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "Cattle to Dairyfarm created successfully") })
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> updatedairyfarm(@RequestBody DFCattleDTO dairyfarmCattleDTO) {
		return dairyfarmCattleService.updateDFCattle(dairyfarmCattleDTO);
	}

	@Operation(summary = "Fetch dairyfarm's Cattle  details", description = "Fetch dairyfarm's Cattle  details")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), responseCode = "200") })
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DFCattleDTO> getdairyfarmDetailsById(@PathVariable("id") long dairyfarmCattleId) {
		return dairyfarmCattleService.fetchDFCattleDetails(dairyfarmCattleId);
	}

	@Operation(summary = "Delete Cattle", description = "Delete Cattle")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "Cattle de-associated with Dairyfarm successfully") })
	@DeleteMapping(value = "/delete/{cattleId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> deleteUser(@PathVariable("cattleId") long cattleId) {
		return dairyfarmCattleService.deleteCattle(cattleId);
	}

	@Operation(summary = "Change Cattle Status", description = "Change Cattle Status")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "Cattle Status changed successfully") })
	@PutMapping(value = "/changestatus/{cattleId}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> deleteUser(@PathVariable("cattleId") long cattleId,
			@PathVariable("status") CattleStatus status) {
		return dairyfarmCattleService.changeCattleStatus(cattleId, status);
	}

}
