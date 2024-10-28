package com.dairyfarm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.dto.UploadedMediaResponse;
import com.dairyfarm.enums.EntityName;
import com.dairyfarm.service.MediaFilesUploadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Oct 8, 2021
 */
@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaFilesUploadController {

	private final MediaFilesUploadService mediaFilesUploadService;

	@Operation(summary = "Add Mediafiles", description = "Add Media")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE), responseCode = "201", description = "Media added successfully") })
	@PostMapping(value = "/upload/{entity}/{entityId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> addMediafilesForUser(
			@RequestPart("files") @Valid @NotNull @NotEmpty MultipartFile[] files,
			@PathVariable("entity") EntityName entityName, @PathVariable("entityId") long entityId) {
		return mediaFilesUploadService.addMediaFiles(files, entityName, entityId);
	}

	@Operation(summary = "Get Media", description = "Add Media")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), responseCode = "201", description = "Media added successfully") })
	@GetMapping(value = "/all/{entity}/{entityId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, List<UploadedMediaResponse>>> getMediaFilesByEntityId(
			@PathVariable("entity") EntityName entityName, @PathVariable("entityId") long entityId) {
		return mediaFilesUploadService.getMediaFileLinks(entityName, entityId);
	}

	@Operation(summary = "Add Media thumbnail", description = "Add Media thumbnail")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE), responseCode = "201", description = "Media added successfully") })
	@PutMapping(value = "/addthumbnail/{entity}/{entityId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> addMediaThumbnail(
			@RequestPart("thumbnail") @Valid @NotNull @NotEmpty MultipartFile file,
			@PathVariable("entity") EntityName entityName, @PathVariable("entityId") long entityId) {
		return mediaFilesUploadService.addMediaThumbnail(file, entityName, entityId);
	}

	@Operation(summary = "Update Media thumbnail", description = "Add Media thumbnail")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE), responseCode = "201", description = "Media added successfully") })
	@PutMapping(value = "/updatethumbnail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> updateMediaThumbnailById(@PathVariable("id") long id) {
		return mediaFilesUploadService.updateMediaThumbnail(id);
	}

	@Operation(summary = "Get Media thumbnail", description = "Add Media thumbnail")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE), responseCode = "201", description = "Media added successfully") })
	@GetMapping(value = "/thumbnail/{entityId}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<byte[]> getMediaThumbnailByEntityId(@PathVariable("entityId") long entityId) {
		return mediaFilesUploadService.getMediaThumbnail(entityId);
	}

	@Operation(summary = "Get Media by Id", description = "Get Media by Id")
	@ApiResponses({
			@ApiResponse(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE), responseCode = "201", description = "Get Media by Id") })
	@GetMapping(value = "/{id}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<byte[]> getMediaById(@PathVariable("id") long id) {
		return mediaFilesUploadService.getMediaById(id);
	}

}
