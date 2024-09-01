package com.dairyfarm.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.dto.UploadedMediaResponse;
import com.dairyfarm.enums.EntityName;

/**
 * @author Pavankumar - created date : Oct 8, 2021
 */
public interface MediaFilesUploadService {

	ResponseEntity<SuccessResponse> addMediaFiles(@Valid @NotNull @NotEmpty MultipartFile[] files, EntityName entity,
			long entityId);

	ResponseEntity<Map<String, List<UploadedMediaResponse>>> getMediaFileLinks(EntityName entityName, long userId);

	ResponseEntity<byte[]> getMediaThumbnail(long entityId);

	ResponseEntity<byte[]> getMediaById(long id);

	ResponseEntity<SuccessResponse> updateMediaThumbnail(long id);

	ResponseEntity<SuccessResponse> addMediaThumbnail(@Valid @NotNull @NotEmpty MultipartFile file, EntityName valueOf,
			long entityId);

}