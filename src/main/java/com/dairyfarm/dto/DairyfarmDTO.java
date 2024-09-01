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
public class DairyfarmDTO extends BaseDTO {

	private static final long serialVersionUID = 8524720990023625825L;

	private String name;

	private String registrationDetails;

	private AddressDTO address;

	private boolean defaultSelection;

	private UserDTO user;

}
