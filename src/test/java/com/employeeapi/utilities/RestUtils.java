package com.employeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

    public static String empName() {
        String generatedString = RandomStringUtils.randomAlphabetic(1);

        return "John" + generatedString;
    }

    public static String empSal() {
        return RandomStringUtils.randomNumeric(5);
    }

    public static String empAge() {
        return RandomStringUtils.randomNumeric(2);
    }
}
