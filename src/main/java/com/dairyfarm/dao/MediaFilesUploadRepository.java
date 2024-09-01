package com.dairyfarm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dairyfarm.entity.UploadMedia;
import com.dairyfarm.enums.EntityName;
import com.dairyfarm.enums.MediaFileType;

/**
 * @author Pavankumar - created date : Oct 9, 2021
 */
@Repository
public interface MediaFilesUploadRepository extends JpaRepository<UploadMedia, Long> {

	List<UploadMedia> findAllByEntityId(long entityId);

	List<UploadMedia> findAllByEntityAndEntityId(EntityName entityName, long entityId);

	UploadMedia findByEntityIdAndMediaFileTypeAndIsThumbnail(long entityId, MediaFileType mediaFileType,
			boolean isThumbnail);

	List<UploadMedia> findAllByEntityIdAndMediaFileType(long entityId, MediaFileType mediaFileType);

	long countByEntityIdAndMediaFileType(long entityId, MediaFileType mediaFileType);

	long countByEntityId(long entityId);

	long countByEntityAndEntityId(EntityName entityName, long entityId);

}
