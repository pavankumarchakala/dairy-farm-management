package com.dairyfarm.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.dairyfarm.constant.UploadedMediaFileConstants;
import com.dairyfarm.dao.MediaFilesUploadRepository;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.dto.UploadedMediaResponse;
import com.dairyfarm.entity.UploadMedia;
import com.dairyfarm.enums.EntityName;
import com.dairyfarm.enums.MediaFileType;
import com.dairyfarm.exceptions.DairyfarmmanagementException;
import com.dairyfarm.util.MediaUploadUtils;
import com.dairyfarm.util.RandomUtils;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Oct 8, 2021
 */
@Service
@RequiredArgsConstructor
public class MediaFilesUploadServiceImpl implements MediaFilesUploadService {

	private final MediaFilesUploadRepository mediaFilesUploadRepository;

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> addMediaFiles(@Valid @NotNull @NotEmpty MultipartFile[] files,
			EntityName entity, long entityId) throws DairyfarmmanagementException {
		String dirPath = MediaUploadUtils.constructDirectoryPath(entity.getDislayValue(), entityId);
		Arrays.asList(files).stream().forEach(file -> {

			try {
				addMediaFile(file, dirPath, entity, entityId, false);
			} catch (IOException e) {
			}

		});
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().message("File(s) uploaded successfully").status(HttpStatus.CREATED).build(),
				HttpStatus.CREATED);
	}

	private void addMediaFile(@Valid @NotNull @NotEmpty MultipartFile file, String dirPath, EntityName entity,
			long entityId, boolean isThumbnail) throws IOException {
		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		String fileName = String.format("%s.%s", RandomUtils.getRandomString(), fileExtension);
		String location = new StringBuilder(dirPath).append(File.separator).append(fileName).toString();
		Path path = Paths.get(location);
		Files.write(path, file.getBytes());
		mediaFilesUploadRepository.save(UploadMedia.builder()
				.originalFileName(Paths.get(file.getOriginalFilename()).getFileName().toString())
				.savedFileName(fileName).entityId(entityId).entity(entity).filePath(location).isThumbnail(isThumbnail)
				.mediaFileType(MediaUploadUtils.findMediaFileType(fileExtension)).fileExtension(fileExtension).build());
	}

	@Override
	@Transactional
	public ResponseEntity<Map<String, List<UploadedMediaResponse>>> getMediaFileLinks(EntityName entityName,
			long entityId) {
		if (mediaFilesUploadRepository.countByEntityAndEntityId(entityName, entityId) == 0)
			throw new DairyfarmmanagementException("There are no media files", HttpStatus.NO_CONTENT);
		List<UploadMedia> list = mediaFilesUploadRepository.findAllByEntityAndEntityId(entityName, entityId);
		Map<String, List<UploadedMediaResponse>> map = new HashMap<>();
		List<UploadedMediaResponse> imageLinks = new ArrayList<>();
		List<UploadedMediaResponse> videoLinks = new ArrayList<>();
		List<UploadedMediaResponse> otherFileLinks = new ArrayList<>();
		list.stream().forEach(item -> {
			String link = "/media/" + item.getId();
			if (MediaFileType.IMAGE.equals(item.getMediaFileType()))
				imageLinks.add(UploadedMediaResponse.builder().imageLink(link).uploadedRecordId(item.getId())
						.entity(entityName).entityId(entityId).build());
			else if (MediaFileType.VIDEO.equals(item.getMediaFileType()))
				videoLinks.add(UploadedMediaResponse.builder().imageLink(link).uploadedRecordId(item.getId())
						.entity(entityName).entityId(entityId).build());
			else
				otherFileLinks.add(UploadedMediaResponse.builder().imageLink(link).uploadedRecordId(item.getId())
						.entity(entityName).entityId(entityId).build());
		});
		map.put(MediaFileType.IMAGE.getDislayValue(), imageLinks);
		map.put(MediaFileType.VIDEO.getDislayValue(), videoLinks);
		map.put(MediaFileType.OTHERS.getDislayValue(), otherFileLinks);
		return new ResponseEntity<Map<String, List<UploadedMediaResponse>>>(map, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<byte[]> getMediaThumbnail(long entityId) {
		UploadMedia uploadMedia = mediaFilesUploadRepository.findByEntityIdAndMediaFileTypeAndIsThumbnail(entityId,
				MediaFileType.IMAGE, true);

		if (null == uploadMedia) {
			throw new DairyfarmmanagementException(
					"There are no thumbnails. Please add or select from existing images.", HttpStatus.NO_CONTENT);
		}

		byte[] thumbnail = null;

		try {
			thumbnail = getFile(uploadMedia.getFilePath());
		} catch (IOException e) {
		}

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add(UploadedMediaFileConstants.CONTENT_DISPOSITION, "attachment;");
		return new ResponseEntity<byte[]>(thumbnail, map, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<byte[]> getMediaById(long id) {
		Optional<UploadMedia> optionalUploadMedia = mediaFilesUploadRepository.findById(id);
		if (!optionalUploadMedia.isPresent())
			throw new DairyfarmmanagementException("No record found with the provided ID", HttpStatus.NOT_FOUND);
		byte[] media = null;

		try {
			media = getFile(optionalUploadMedia.get().getFilePath());
		} catch (IOException e) {
			throw new DairyfarmmanagementException(e.getMessage(), e);
		}

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add(UploadedMediaFileConstants.CONTENT_DISPOSITION, "attachment;");
		return new ResponseEntity<byte[]>(media, map, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> updateMediaThumbnail(long id) {
		Optional<UploadMedia> optionalUploadMedia = mediaFilesUploadRepository.findById(id);
		if (!optionalUploadMedia.isPresent())
			throw new DairyfarmmanagementException("No record found with the provided ID", HttpStatus.NOT_FOUND);
		UploadMedia uploadMedia = optionalUploadMedia.get();
		uploadMedia.setThumbnail(true);
		deselectThumbnailSelection(uploadMedia.getEntityId(), id);
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().message("Thumbnail updated successfully").status(HttpStatus.OK).build(),
				HttpStatus.OK);
	}

	private void deselectThumbnailSelection(long entityId, long uploadMediaId) {
		mediaFilesUploadRepository.findAllByEntityIdAndMediaFileType(entityId, MediaFileType.IMAGE).stream()
				.forEach(item -> {
					if (0 == uploadMediaId || item.getId() != uploadMediaId)
						item.setThumbnail(false);
				});
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> addMediaThumbnail(@Valid @NotNull @NotEmpty MultipartFile file,
			EntityName entity, long entityId) {

		try {
			deselectThumbnailSelection(entityId, 0);
			String dirPath = MediaUploadUtils.constructDirectoryPath(entity.getDislayValue(), entityId);
			addMediaFile(file, dirPath, entity, entityId, true);
		} catch (IOException e) {
			throw new DairyfarmmanagementException(e.getMessage(), e);
		}

		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().message("Thumbnail uploaded successfully").status(HttpStatus.CREATED).build(),
				HttpStatus.CREATED);
	}

	private byte[] getFile(String path) throws IOException {
		File file = new File(path);
		byte[] b = null;

		if (file.exists()) {
			b = FileUtils.readFileToByteArray(file);
		}

		return b;
	}

}
