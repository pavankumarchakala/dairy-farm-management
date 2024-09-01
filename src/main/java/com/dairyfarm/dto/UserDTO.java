package com.dairyfarm.dto;

import com.dairyfarm.enums.UserRole;

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
public class UserDTO extends BaseDTO {

	private static final long serialVersionUID = 3673432348254489323L;

	private String name;

	private String mobile;

	private String email;

	private UserRole role;

	private AddressDTO address;

}
