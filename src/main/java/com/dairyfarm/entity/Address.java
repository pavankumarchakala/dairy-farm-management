package com.dairyfarm.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author Pavankumar - created date : Oct 6, 2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Embeddable
public class Address {

	@Column(name = "door_num")
	private String doorNum;

	@Column(name = "street_name")
	private String streetName;

	@Column(name = "landmark")
	private String landmark;

	@NonNull
	@Column(name = "district")
	private String district;

	@NonNull
	@Column(name = "state")
	private String state;

	@Column(name = "pincode")
	private int pincode;

}
