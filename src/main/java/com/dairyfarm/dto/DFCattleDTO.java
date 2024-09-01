package com.dairyfarm.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class DFCattleDTO extends BaseDTO {

	private static final long serialVersionUID = -8457959515209935809L;

	private String breed;

	private String name;

	private String tagNum;

	private Gender gender;

	private String stage;

	private LocalDateTime dateOfBirth;

	private LocalDateTime dateOfEntryToFarm;

	private String obtainedFrom;

	private String motherTagNum;

	private String fatherTagNum;

	private BigDecimal purchasedAmount;

	private CattleStatus status;

	private DairyfarmDTO dairyfarm;

}
