package com.dairyfarm.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Pavankumar - created date : Sep 13, 2021
 */
public enum CattleStatus {

	INACTIVE("Inactive"),
	ACTIVE("Active"),
	SOLD("Sold"),
	DEAD("Dead");

	private String displayValue;

	private CattleStatus(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDislayValue() {
		return displayValue;
	}

	public static CattleStatus getValueOf(String value) {
		Optional<CattleStatus> filteredItem = Arrays.asList(CattleStatus.values()).stream()
				.filter(item -> item.name().toLowerCase().equals(value.toLowerCase())).findFirst();
		if (filteredItem.isPresent())
			return filteredItem.get();
		return null;
	}

	public static List<String> getAllDisplayValues() {
		return Arrays.asList(CattleStatus.values()).stream().map(item -> item.displayValue)
				.collect(Collectors.toList());
	}

}
