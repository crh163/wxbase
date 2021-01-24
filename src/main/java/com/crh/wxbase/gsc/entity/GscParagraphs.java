package com.crh.wxbase.gsc.entity;

import com.crh.wxbase.common.base.BaseModel;
import lombok.Data;

/**
 * @author rory.chen
 * @date 2021-01-22 16:46
 */
@Data
public class GscParagraphs extends BaseModel {

    private Long rhythmicId;

    private String text;

    private Integer parOrder;

}
