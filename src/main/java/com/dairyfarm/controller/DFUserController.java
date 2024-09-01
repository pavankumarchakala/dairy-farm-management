package com.dairyfarm.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dairyfarm.dto.DFUserDTO;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.service.DFUserService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Sep 15, 2021
 */
@RestController
@RequestMapping("/dairyfarmuser")
@RequiredArgsConstructor
public class DFUserController {

	private final DFUserService dairyfarmUserService;

	@Operation(summary = "Add DairyfarmUser", description = "Add DairyfarmUser with it's User details")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "201",
			description = "DairyfarmUser created successfully") })
	@PostMapping(
			value = "/add",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Hidden
	public ResponseEntity<SuccessResponse> adddairyfarmUser(@RequestBody DFUserDTO dairyfarmUserDTO) {
		return dairyfarmUserService.adddairyfarmUser(dairyfarmUserDTO);
	}

	@Operation(summary = "Update DairyfarmUser", description = "Update DairyfarmUser")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "DairyfarmUser created successfully") })
	@PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Hidden
	public ResponseEntity<SuccessResponse> updatedairyfarmUser(@RequestBody DFUserDTO dairyfarmUserDTO) {
		return dairyfarmUserService.updatedairyfarmUser(dairyfarmUserDTO);
	}

	@Operation(summary = "De-Associate all the Dairyfarms for a User", description = "Delete DairyfarmUser")
	@ApiResponses({ @ApiResponse(
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			responseCode = "200",
			description = "DairyfarmUser created successfully") })
	@PostMapping(value = "/delete/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Hidden
	public ResponseEntity<SuccessResponse> updatedairyfarmUser(@PathVariable("userId") long userId) {
		return dairyfarmUserService.deassociateAlldairyfarmsForAnUser(userId);
	}

}
