package com.dairyfarm.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name = "tb_df_income_cat_item")
@DynamicInsert
@DynamicUpdate
public class DFIncomeCategoryItem extends BaseEntity {

	private static final long serialVersionUID = -7099159470281547075L;

	@Column(name = "item")
	private String item;

	@Column(name = "unit_price", columnDefinition = "Decimal(10,2) default '0.00'")
	private BigDecimal unitPrice;

	@ManyToOne(targetEntity = DFIncomeCategory.class)
	@JoinColumn(name = "df_income_category_id", nullable = false)
	private DFIncomeCategory category;

}
