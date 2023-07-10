package com.app.emp.util;

public enum RegexUtil {
    EMAIL("^\\d{10}$");

    private final String value;

    private RegexUtil(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

