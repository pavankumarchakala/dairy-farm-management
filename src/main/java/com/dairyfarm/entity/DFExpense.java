package com.dairyfarm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "tb_df_expense")
@DynamicInsert
@DynamicUpdate
public class DFExpense extends BaseEntity {

	private static final long serialVersionUID = -7727675754280476924L;

	@Column(name = "expense", columnDefinition = "Decimal(10,2) default '0.00'")
	private BigDecimal expense;

	@Column(name = "invoice")
	private String invoiceNum;

	@Lob
	@Column(name = "description")
	private String description;

	@Column(name = "paid_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime paidDate;

	@ManyToOne(targetEntity = DFExpenseCategoryItem.class)
	@JoinColumn(name = "df_expense_item_id")
	private DFExpenseCategoryItem item;

	@ManyToOne(targetEntity = DFExpenseCategory.class)
	@JoinColumn(name = "df_expense_category_id")
	private DFExpenseCategory category;

	@ManyToOne(targetEntity = Dairyfarm.class)
	@JoinColumn(name = "df_id", nullable = false)
	private Dairyfarm dairyfarm;

}
