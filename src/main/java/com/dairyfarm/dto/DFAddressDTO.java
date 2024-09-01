package com.dairyfarm.dto;

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
public class DFAddressDTO extends BaseDTO {

	private static final long serialVersionUID = 7786341435959565071L;

	private String surveyNum;

	private String landmark;

	private String district;

	private String state;

	private int pincode;

}
