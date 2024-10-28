package com.dairyfarm.entity;

import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.dairyfarm.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author Pavankumar - created date : Sep 13, 2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@Entity
@Table(name = "tb_dairyfarm")
@DynamicInsert
@DynamicUpdate
public class Dairyfarm extends BaseEntity {

	private static final long serialVersionUID = -2740846448812015409L;

	@NonNull
	@Column(name = "name")
	private String name;

	@Column(name = "registration_details")
	private String registrationDetails;

	@Embedded
	private Address address;

	@Column(name = "default_selection", columnDefinition = "tinyint(1) default 0")
	private boolean defaultSelection;

	@OneToMany(mappedBy = "dairyfarm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DFUser> dairyfarmUsers;

	@Column(name = "website")
	private String website;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", columnDefinition = "varchar(32) not null default 'ACTIVE'")
	private Status status;

}
