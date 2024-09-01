package com.dairyfarm.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Pavankumar - created date : Sep 18, 2021
 */
public enum Gender {

	MALE("Male"),
	FEMALE("Female"),
	OTHER("Other");

	private String displayValue;

	private Gender(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDislayValue() {
		return displayValue;
	}

	public static Gender getValueOf(String value) {
		Optional<Gender> filteredItem = Arrays.asList(Gender.values()).stream()
				.filter(item -> item.name().toLowerCase().equals(value.toLowerCase())).findFirst();
		if (filteredItem.isPresent())
			return filteredItem.get();
		return null;
	}

	public static List<String> getAllDisplayValues() {
		return Arrays.asList(Gender.values()).stream().map(item -> item.displayValue).collect(Collectors.toList());
	}

}
