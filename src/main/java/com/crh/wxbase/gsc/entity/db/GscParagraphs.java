package com.crh.wxbase.gsc.entity.db;

import com.crh.wxbase.common.entity.base.BaseModel;
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
