package com.dairyfarm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@Entity
@Table(name = "tb_df_income")
@DynamicInsert
@DynamicUpdate
public class DFIncome extends BaseEntity {

	private static final long serialVersionUID = -4459493089245485602L;

	@Column(name = "income", columnDefinition = "Decimal(10,2) default '0.00'")
	private BigDecimal income;

	@Column(name = "receipt")
	private String receiptNum;

	@Lob
	@Column(name = "description")
	private String description;

	@Column(name = "received_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime receivedDate;

	@Column(name = "item_quantity")
	private int itemQuantity;

	@ManyToOne(targetEntity = DFIncomeCategoryItem.class)
	@JoinColumn(name = "df_income_item_id")
	private DFIncomeCategoryItem item;

	@ManyToOne(targetEntity = DFIncomeCategory.class)
	@JoinColumn(name = "df_income_category_id")
	private DFIncomeCategory category;

	@ManyToOne(targetEntity = Dairyfarm.class)
	@JoinColumn(name = "df_id", nullable = false)
	private Dairyfarm dairyfarm;

}
