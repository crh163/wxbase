package com.crh.wxbase.gsc.entity.db;

import com.crh.wxbase.common.entity.base.BaseModel;
import lombok.Data;

/**
 * @author rory.chen
 * @date 2021-01-22 16:46
 */
@Data
public class GscVisitRhythmicHistory extends BaseModel {

    /**
     * 微信用户openid
     */
    private String wxuserOpenId;

    /**
     * 诗词id
     */
    private Long rhythmicId;

    /**
     * 访问时间（天）
     */
    private String visitDate;

}
