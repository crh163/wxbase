package com.crh.wxbase.gsc.entity.db;

import com.crh.wxbase.common.entity.base.BaseModel;
import lombok.Data;

/**
 * @author rory.chen
 * @date 2021-01-22 16:46
 */
@Data
public class GscSearchHistory extends BaseModel {

    /**
     * 微信用户openid
     */
    private String wxuserOpenId;

    /**
     * 搜索内容
     */
    private String searchText;

    /**
     * 响应json数据
     */
    private String returnData;

    /**
     * 搜索时间（天）
     */
    private String visitDate;

}
