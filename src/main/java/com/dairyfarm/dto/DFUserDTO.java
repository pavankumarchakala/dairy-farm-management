package com.dairyfarm.dto;

import com.dairyfarm.enums.Status;

import io.swagger.v3.oas.annotations.Hidden;
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
public class DFUserDTO extends BaseDTO {

	private static final long serialVersionUID = -3459728605532387951L;

	private DairyfarmDTO dairyfarm;

	private UserDTO user;

	@Hidden
	private Status status;

}
