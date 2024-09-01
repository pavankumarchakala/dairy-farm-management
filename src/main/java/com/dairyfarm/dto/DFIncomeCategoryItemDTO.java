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
public class DFIncomeCategoryItemDTO extends BaseDTO {

	private static final long serialVersionUID = -8787098147146221920L;

	private String item;

	private BigDecimal unitPrice;

	private DFIncomeCategoryDTO category;

}
