package com.dairyfarm.dto;

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
public class UserAddressDTO extends BaseDTO {

	private static final long serialVersionUID = -475452650225133812L;

	private String doorNumOrFlatNum;

	private String streetName;

	private String landmark;

	@NonNull
	private String district;

	@NonNull
	private String state;

	private int pincode;

}
