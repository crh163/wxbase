package com.crh.wxbase.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crh.wxbase.common.constant.ColumnConsts;
import com.crh.wxbase.common.constant.UserLevelConsts;
import com.crh.wxbase.common.service.BaseService;
import com.crh.wxbase.system.entity.SysMenu;
import com.crh.wxbase.system.entity.SysUser;
import com.crh.wxbase.system.entity.dto.SysMenuDto;
import com.crh.wxbase.system.mapper.SysMenuMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author rory.chen
 * @date 2021-01-21 18:06
 */
@Service
public class SysMenuService extends BaseService<SysMenuMapper, SysMenu> {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 根据用户名查询菜单
     *
     * @param username
     */
    public List<SysMenuDto> queryMenu(String username) {
        SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().eq(ColumnConsts.USERNAME, username));
        //如果是超级管理员，则直接查出所有的菜单
        List<SysMenuDto> menusDto = new ArrayList<>();
        if (UserLevelConsts.LEVEL_ADMIN.equals(sysUser.getLevelId())) {
            List<SysMenu> sysMenus = list();
            Map<Long, List<SysMenuDto>> menuMap = new HashMap<>();
            for (SysMenu sysMenu : sysMenus) {
                List<SysMenuDto> commonPidList = Objects.isNull(menuMap.get(sysMenu.getPid()))
                        ? new ArrayList<>() : menuMap.get(sysMenu.getPid());
                SysMenuDto sysMenuDto = new SysMenuDto();
                BeanUtils.copyProperties(sysMenu, sysMenuDto);
                commonPidList.add(sysMenuDto);
                menuMap.put(sysMenu.getPid(), commonPidList);
            }
            for (SysMenu sysMenu : sysMenus) {
                if (Objects.nonNull(sysMenu.getPid())) {
                    continue;
                }
                SysMenuDto sysMenuDto = new SysMenuDto();
                BeanUtils.copyProperties(sysMenu, sysMenuDto);
                List<SysMenuDto> sysMenuDtos = menuMap.get(sysMenu.getId());
                cycleAddChildMenus(sysMenuDtos, menuMap);
                sysMenuDto.setSysMenus(sysMenuDtos);
                menusDto.add(sysMenuDto);
            }
        }
        return menusDto;
    }


    /**
     * 循环封装
     *
     * @param menusDto
     * @param menuMap
     */
    private void cycleAddChildMenus(List<SysMenuDto> menusDto, Map<Long, List<SysMenuDto>> menuMap) {
        for (SysMenuDto sysMenuDto : menusDto) {
            List<SysMenuDto> sysMenuDtos = menuMap.get(sysMenuDto.getId());
            if(Objects.nonNull(sysMenuDtos)){
                cycleAddChildMenus(sysMenuDtos, menuMap);
                sysMenuDto.setSysMenus(sysMenuDtos);
            }
        }
    }

}
