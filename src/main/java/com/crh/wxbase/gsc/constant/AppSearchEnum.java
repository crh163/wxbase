package com.crh.wxbase.gsc.constant;

/**
 * @author rory.chen
 * @date 2021-03-22 16:12
 */
public enum AppSearchEnum {

    /**
     * 搜索
     */
    ALL(0, "综合查询"),
    AUTHOR(1, "作者"),
    RHYTHMIC(2, "标题"),
    PARAGRAPHS(3, "诗句");

    private Integer code;

    private String msg;

    AppSearchEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static AppSearchEnum getEnumByCode(Integer code) {
        for (AppSearchEnum enums : AppSearchEnum.values()) {
            if (enums.code.equals(code)) {
                return enums;
            }
        }
        return null;
    }

}
