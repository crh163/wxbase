package com.crh.wxbase.common.utils;

import com.crh.wxbase.common.config.exception.WxbaseException;
import com.crh.wxbase.common.constant.ResponseCodeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rory.chen
 * @date 2021-02-26 13:50
 */
public class CollectionUtil {

    /**
     * 字符串转集合
     *
     * @param values 1,2,3
     * @return
     */
    public static List<Long> converIdsToList(String values) throws WxbaseException {
        if(StringUtils.isBlank(values)){
            throw new WxbaseException(ResponseCodeEnum.FAIL_ALL_DELETE_NULL_ID);
        }
        return Arrays.stream(values.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

}
