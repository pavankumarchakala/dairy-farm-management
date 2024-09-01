package com.dairyfarm.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Pavankumar - created date : Sep 13, 2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 5952868229579386586L;

	@Id
	@GenericGenerator(name = "id_generator", strategy = "com.dairyfarm.config.CustomIdGenerator")
	@GeneratedValue(generator = "id_generator")
	@Column(name = "id")
	private long id;

	@CreationTimestamp
	@Column(name = "created_date", updatable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime createdDate;

	@UpdateTimestamp
	@Column(name = "updated_date", insertable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime updatedDate;

}
