package com.dairyfarm.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class DFExpenseDTO extends BaseDTO {

	private static final long serialVersionUID = 6267835209261614626L;

	private BigDecimal expense;

	private String invoiceNum;

	private String description;

	private LocalDateTime paidDate;

	private DFExpenseCategoryDTO category;

	private DFExpenseCategoryItemDTO item;

	private DairyfarmDTO dairyfarm;

}
