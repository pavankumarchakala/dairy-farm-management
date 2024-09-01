package com.dairyfarm.dto;

import java.io.Serializable;

import com.dairyfarm.enums.EntityName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Pavankumar - created date : Oct 25, 2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class UploadedMediaResponse implements Serializable {

	private static final long serialVersionUID = -253974814188171753L;

	private long uploadedRecordId;

	private String imageLink;

	private long entityId;

	private EntityName entity;

}
