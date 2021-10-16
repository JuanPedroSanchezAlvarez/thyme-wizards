package com.thymewizards.util;

public final class LoggingUtils {

	private LoggingUtils() {}

	public static String getCurrentMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

}
