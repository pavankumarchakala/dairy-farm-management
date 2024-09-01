package com.dairyfarm.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Pavankumar - created date : Oct 10, 2021
 */
public enum MediaFileType {

	IMAGE("Image"),
	VIDEO("Video"),
	OTHERS("Others");

	private String displayValue;

	private MediaFileType(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDislayValue() {
		return displayValue;
	}

	public static MediaFileType getValueOf(String value) {
		Optional<MediaFileType> filteredItem = Arrays.asList(MediaFileType.values()).stream()
				.filter(item -> item.name().toLowerCase().equals(value.toLowerCase())).findFirst();
		if (filteredItem.isPresent())
			return filteredItem.get();
		return null;
	}

	public static List<String> getAllDisplayValues() {
		return Arrays.asList(MediaFileType.values()).stream().map(item -> item.displayValue)
				.collect(Collectors.toList());
	}

}
