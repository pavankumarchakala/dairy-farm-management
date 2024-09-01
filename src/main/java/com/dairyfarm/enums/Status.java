package com.dairyfarm.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Pavankumar - created date : Oct 4, 2021
 */
public enum Status {

	ACTIVE("Active"),
	INACTIVE("Inactive");

	private String displayValue;

	private Status(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDislayValue() {
		return displayValue;
	}

	public static Status getValueOf(String value) {
		Optional<Status> filteredItem = Arrays.asList(Status.values()).stream()
				.filter(item -> item.name().toLowerCase().equals(value.toLowerCase())).findFirst();
		if (filteredItem.isPresent())
			return filteredItem.get();
		return null;
	}

	public static List<String> getAllDisplayValues() {
		return Arrays.asList(Status.values()).stream().map(item -> item.displayValue).collect(Collectors.toList());
	}

}
