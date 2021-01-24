package com.crh.wxbase.gsc.entity;

import com.crh.wxbase.common.base.BaseModel;
import lombok.Data;

/**
 * @author rory.chen
 * @date 2021-01-22 16:46
 */
@Data
public class GscAuthor extends BaseModel {

    private String name;

    private String description;

    private String shortDescription;

}
