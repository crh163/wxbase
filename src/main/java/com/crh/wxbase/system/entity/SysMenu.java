package com.crh.wxbase.system.entity;

import com.crh.wxbase.common.entity.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author rory.chen
 * @date 2021-01-12 18:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysMenu extends BaseModel {

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 菜单跳转链接
     */
    private String url;

}
