package com.dairyfarm.entity;

import java.math.BigDecimal;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Pavankumar - created date : Oct 14, 2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@Entity
@Table(name = "tb_df_income_category")
@DynamicInsert
@DynamicUpdate
public class DFIncomeCategory extends BaseEntity {

	private static final long serialVersionUID = -4136063322846539L;

	@Column(name = "category")
	private String category;

	@Column(name = "unit_price", columnDefinition = "Decimal(10,2) default '0.00'")
	private BigDecimal unitPrice;

	@ManyToOne(targetEntity = Dairyfarm.class)
	@JoinColumn(name = "df_id", nullable = false)
	private Dairyfarm dairyfarm;

}
