package com.dairyfarm.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.dairyfarm.enums.EntityName;
import com.dairyfarm.enums.MediaFileType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Pavankumar - created date : Oct 8, 2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@Entity
@Table(name = "tb_upload_media")
@DynamicInsert
@DynamicUpdate
public class UploadMedia extends BaseEntity {

	private static final long serialVersionUID = -479041230581678741L;

	@Column(name = "entity_id")
	public long entityId;

	@Lob
	@Column(name = "file_path")
	public String filePath;

	@Column(name = "saved_file_name")
	public String savedFileName;

	@Column(name = "original_file_name")
	public String originalFileName;

	@Column(name = "file_extension")
	public String fileExtension;

	@Column(name = "is_thumbnail", columnDefinition = "tinyint(1) default 0")
	private boolean isThumbnail;

	@Enumerated(EnumType.STRING)
	@Column(name = "entity")
	private EntityName entity;

	@Enumerated(EnumType.STRING)
	@Column(name = "media_type", columnDefinition = "varchar(32) default 'IMAGE'")
	private MediaFileType mediaFileType;

}
