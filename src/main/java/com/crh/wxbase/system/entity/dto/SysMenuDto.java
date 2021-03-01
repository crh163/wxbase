package com.crh.wxbase.system.entity.dto;

import com.crh.wxbase.system.entity.dto.struct.MenuMeta;
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
     *
     */
    private String component;

    /**
     * 是否需要登录访问结构
     */
    private MenuMeta meta;

    /**
     * 子菜单
     */
    private List<SysMenuDto> children;

}
