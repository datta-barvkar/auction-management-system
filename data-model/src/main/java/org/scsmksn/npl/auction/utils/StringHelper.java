package org.scsmksn.npl.auction.utils;

public class StringHelper {

    private static final String TEXT_KEY_TEXT_VALUE_FORMAT = "\"%s\": \"%s\"";
    private static final String TEXT_KEY_GENERIC_VALUE_FORMAT = "\"%s\": %s";
    private static final String TEXT_KEY_ARRAY_VALUE_FORMAT = "\"%s\": [%s]";

    public static String getTextKeyTextValue(final String key, final String value) {

        return String.format(TEXT_KEY_TEXT_VALUE_FORMAT, key, value);
    }

    public static String getTextKeyArrayValue(final String key, final String value) {

        return String.format(TEXT_KEY_ARRAY_VALUE_FORMAT, key, value);
    }

    public static String getTextKeyGenericValue(final String key, final String value) {

        return String.format(TEXT_KEY_GENERIC_VALUE_FORMAT, key, value);
    }
}
