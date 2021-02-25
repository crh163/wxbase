package com.crh.wxbase.system.entity.dto;

import com.crh.wxbase.system.entity.SysMenu;
import lombok.Data;

import java.util.List;

/**
 * @author rory.chen
 * @date 2021-02-25 16:36
 */
@Data
public class SysMenuDto {

    /**
     * id
     */
    private Long id;

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

    /**
     * 子菜单
     */
    private List<SysMenuDto> sysMenus;

}
