package com.dairyfarm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Pavankumar - created date : Oct 6, 2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class AddressDTO {

	private String doorNum;

	private String streetName;

	private String landmark;

	private String district;

	private String state;

	private int pincode;

}
