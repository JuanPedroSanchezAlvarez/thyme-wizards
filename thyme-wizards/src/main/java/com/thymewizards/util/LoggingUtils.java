package com.thymewizards.util;

public final class LoggingUtils {

	private LoggingUtils() {}

	/**
     * Get the name of the method currently running
     */
	public static String getCurrentMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

}
