package com.hdjunction.project.yunsang.global.util;

/**
 * 매직리터럴 -> 상수 관리 Class
 */
public class ConstantUtil {
    public static final String MASTER = "master";
    public static final String SLAVE = "slave";
    public static final String PERCENT = "%";
    public static final String DASH = "-";
    public static final String SCHEMA_LOCATIONS = "/sql/create_schema.sql";
    public static final String DATA_LOCATIONS = "/sql/insert_data.sql";

    private ConstantUtil() {
        throw new IllegalStateException("Utility class.");
    }
}
