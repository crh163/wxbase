package com.crh.wxbase.common.utils;

import com.crh.wxbase.common.constant.CommonConsts;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author rory.chen
 * @date 2021-01-12 17:48
 */
public class TimeUtil {

    public static String converLocalDateToString(LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            return null;
        }
        return localDate.format(CommonConsts.DTF_DAY);
    }


    public static LocalDate converStringToLocalDate(String localDateStr) {
        if (StringUtils.isBlank(localDateStr)) {
            return null;
        }
        return LocalDate.parse(localDateStr, CommonConsts.DTF_DAY);
    }

}
