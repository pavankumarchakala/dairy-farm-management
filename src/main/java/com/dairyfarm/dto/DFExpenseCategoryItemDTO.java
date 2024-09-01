package com.dairyfarm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Pavankumar - created date : Oct 16, 2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class DFExpenseCategoryItemDTO extends BaseDTO {

	private static final long serialVersionUID = 4402505136341592981L;

	private String item;

	private DFExpenseCategoryDTO category;

}
