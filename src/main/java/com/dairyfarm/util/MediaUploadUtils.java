package com.dairyfarm.util;

import java.io.File;
import java.util.Arrays;

import com.dairyfarm.constant.UploadedMediaFileConstants;
import com.dairyfarm.enums.MediaFileType;

/**
 * @author Pavankumar - created date : Oct 8, 2021
 */
public final class MediaUploadUtils {

	private MediaUploadUtils() {
	}

	public static final MediaFileType findMediaFileType(String fileExtension) {

		if (Arrays.asList(UploadedMediaFileConstants.ALL_POSSIBLE_IMAGE_FILE_EXTNS).contains(fileExtension)) {
			return MediaFileType.IMAGE;
		} else if (Arrays.asList(UploadedMediaFileConstants.ALL_POSSIBLE_VIDEO_FILE_EXTNS).contains(fileExtension)) {
			return MediaFileType.VIDEO;
		} else {
			return MediaFileType.OTHERS;
		}

	}

	public final static String constructDirectoryPath(String entityName, long entityId) {
		String dirPath = new StringBuilder(System.getProperty("user.home")).append(File.separator)
				.append("dairyfarmmanagement").append(File.separator).append(entityName).append(File.separator)
				.append(entityId).toString();
		File dir = new File(dirPath);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		return dirPath;
	}

}
