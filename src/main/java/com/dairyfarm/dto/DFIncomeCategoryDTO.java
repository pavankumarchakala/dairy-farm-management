package com.dairyfarm.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Pavankumar - created date : Oct 13, 2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class DFIncomeCategoryDTO extends BaseDTO {

	private static final long serialVersionUID = 5751873283307129120L;

	private String category;

	private BigDecimal unitPrice;

	private DairyfarmDTO dairyfarm;

}
