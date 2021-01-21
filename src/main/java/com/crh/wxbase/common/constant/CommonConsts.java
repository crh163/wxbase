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
     * 匹配括弧及其里面的内容
     */
    public static final String BRACKET = "[(|（].*[)|）]";

    /**
     * 代码生成目录
     */
    public static final String GEN_WINDOW_PATH = "D:\\Users\\rory.chen\\Desktop\\gen\\";

}
