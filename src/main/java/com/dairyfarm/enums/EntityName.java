package com.dairyfarm.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Pavankumar - created date : Oct 7, 2021
 */
public enum EntityName {

	USER("User"),
	DAIRYFARM("Dairyfarm"),
	CATTLE("Cattle"),
	CATEGORY("Category"),
	CATEGORY_ITEM("CategoryItem"),
	UPLOADMEDIA("UploadMedia");

	private String displayValue;

	private EntityName(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDislayValue() {
		return displayValue;
	}

	public static EntityName getValueOf(String value) {
		Optional<EntityName> filteredItem = Arrays.asList(EntityName.values()).stream()
				.filter(item -> item.name().toLowerCase().equals(value.toLowerCase())).findFirst();
		if (filteredItem.isPresent())
			return filteredItem.get();
		return null;
	}

	public static List<String> getAllDisplayValues() {
		return Arrays.asList(EntityName.values()).stream().map(entityName -> entityName.displayValue)
				.collect(Collectors.toList());
	}

}
