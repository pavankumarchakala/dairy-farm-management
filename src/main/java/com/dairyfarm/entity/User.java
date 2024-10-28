package com.dairyfarm.entity;

import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.dairyfarm.enums.Status;
import com.dairyfarm.enums.UserRole;

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
@Table(name = "tb_user")
@DynamicInsert
@DynamicUpdate
public class User extends BaseEntity {

	private static final long serialVersionUID = -1580020784686619386L;

	@NonNull
	@Column(name = "name")
	private String name;

	@NonNull
	@Column(name = "mobile")
	private String mobile;

	@Column(name = "email")
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private UserRole role;

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<DFUser> dairyfarmUsers;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", columnDefinition = "varchar(32) not null default 'ACTIVE'")
	private Status status;

}
