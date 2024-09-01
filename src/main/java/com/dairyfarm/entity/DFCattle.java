package com.dairyfarm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.dairyfarm.enums.CattleStatus;
import com.dairyfarm.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Pavankumar - created date : Sep 13, 2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@Entity
@Table(name = "tb_df_cattle")
@DynamicInsert
@DynamicUpdate
public class DFCattle extends BaseEntity {

	private static final long serialVersionUID = 5259414674257801684L;

	@Column(name = "breed")
	private String breed;

	@Column(name = "name")
	private String name;

	@Column(name = "tag_num")
	private String tagNum;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;

	@Column(name = "stage")
	private String stage;

	@Column(name = "date_of_birth", columnDefinition = "TIMESTAMP")
	private LocalDateTime dateOfBirth;

	@Column(name = "date_of_entry", columnDefinition = "TIMESTAMP")
	private LocalDateTime dateOfEntryToFarm;

	@Column(name = "obtained_from")
	private String obtainedFrom;

	@Column(name = "mother_tag_num")
	private String motherTagNum;

	@Column(name = "father_tag_num")
	private String fatherTagNum;

	@Column(name = "purchased_amount", columnDefinition = "Decimal(10,2) default '0.00'")
	private BigDecimal purchasedAmount;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", columnDefinition = "varchar(32) not null default 'ACTIVE'")
	private CattleStatus status;

	@ManyToOne(targetEntity = Dairyfarm.class)
	@JoinColumn(name = "df_id", nullable = false)
	private Dairyfarm dairyfarm;

}
