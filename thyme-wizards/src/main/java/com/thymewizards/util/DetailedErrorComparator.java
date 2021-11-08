package com.thymewizards.util;

import java.util.Comparator;

import org.thymeleaf.spring5.util.DetailedError;

public class DetailedErrorComparator implements Comparator<DetailedError> {

	/**
     * Sort the error messages based on the name of the corresponding field
     */
	@Override
	public int compare(DetailedError o1, DetailedError o2) {
		return o1.getFieldName().compareTo(o2.getFieldName());
	}

}
