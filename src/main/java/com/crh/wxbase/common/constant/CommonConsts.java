package com.crh.wxbase.common.constant;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * @author rory.chen
 * @date 2021-01-07 14:24
 */
public class CommonConsts {

    /**
     * yyyy-MM-dd
     */
    public static final DateTimeFormatter DTF_DAY = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter DTF_SECONDS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final SimpleDateFormat SDF_SECONDS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 代码生成目录
     */
    public static final String GEN_WINDOW_PATH = "D:\\Users\\rory.chen\\Desktop\\gen\\";

    /**
     *
     */
    public static final String ASC = "asc";

    /**
     *
     */
    public static final String AES = "AES";

    /**
     *
     */
    public static final String X_ACCESS_TOKEN = "x-access-token";

}
