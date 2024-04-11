package com.hdjunction.project.yunsang.global.util;

import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 문자열 가공 Class
 */
public class StringUtil {
    /**
     * 날짜 포맷 타입 String -> LocalDateTime
     */
    public static LocalDateTime setLocalDateTimeFromString(String dateTimeStr, String format) {
        if (ObjectUtils.isEmpty(dateTimeStr)) {
            return null;
        }
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 날짜 포맷 타입 LocalDateTime -> String
     */
    public static String setStringFromLocalDateTime(LocalDateTime dateTime, String format) {
        if (ObjectUtils.isEmpty(dateTime)) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static String whereLikeFormat(String sql) {
        return ConstantUtil.PERCENT + sql + ConstantUtil.PERCENT;
    }

    public static String birthFormat(String birth) {
        if (ObjectUtils.isEmpty(birth) || birth.length() != 8) return birth;

        return birth.substring(0, 4) +
                ConstantUtil.DASH +
                birth.substring(4, 6) +
                ConstantUtil.DASH +
                birth.substring(6);
    }

    private StringUtil() {
        throw new IllegalStateException("Utility class.");
    }
}
