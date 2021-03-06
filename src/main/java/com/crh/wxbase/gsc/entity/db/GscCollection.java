package com.crh.wxbase.gsc.entity.db;

import com.crh.wxbase.common.entity.base.BaseModel;
import lombok.Data;

/**
 * @author rory.chen
 * @date 2021/4/16
 */
@Data
public class GscCollection extends BaseModel {

    /**
     * openId
     */
    private String openId;

    /**
     * 名称
     */
    private String name;

    /**
     * 是否是文件夹 0否 1是
     */
    private String hasFolder;

    /**
     * 诗词id
     */
    private Long collectId;

    /**
     * 文件夹id
     */
    private Long parentFolderId;

}
