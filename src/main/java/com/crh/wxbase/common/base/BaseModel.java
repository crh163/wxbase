package com.crh.wxbase.common.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author rory.chen
 * @date 2021-01-21 17:50
 */
@Data
@Accessors(chain = true)
public class BaseModel {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 创建人id
     */
    private Integer createId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 最近修改人id
     */
    private Integer updateId;

    /**
     * 最近修改时间
     */
    private Date updateDate;

}
