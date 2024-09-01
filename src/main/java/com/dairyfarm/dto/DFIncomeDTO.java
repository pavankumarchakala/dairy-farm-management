package com.dairyfarm.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class DFIncomeDTO extends BaseDTO {

	private static final long serialVersionUID = 927013546935164627L;

	private BigDecimal income;

	private String receiptNum;

	private String description;

	private int itemQuantity;

	private LocalDateTime receivedDate;

	private DFIncomeCategoryDTO category;

	private DFIncomeCategoryItemDTO item;

	private DairyfarmDTO dairyfarm;

}
