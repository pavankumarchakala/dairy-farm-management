package com.dairyfarm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.dairyfarm.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Pavankumar - created date : Sep 15, 2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@Entity
@Table(name = "tb_df_user")
@DynamicInsert
@DynamicUpdate
public class DFUser extends BaseEntity {

	private static final long serialVersionUID = 7929352577048198063L;

	@ManyToOne(targetEntity = Dairyfarm.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "df_id", nullable = false, unique = false)
	private Dairyfarm dairyfarm;

	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, unique = false)
	private User user;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", columnDefinition = "varchar(32) not null default 'ACTIVE'")
	private Status status;

}
