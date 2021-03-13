package com.crh.wxbase.common.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crh.wxbase.common.constant.CommonConsts;
import com.crh.wxbase.common.constant.ResponseCodeEnum;
import com.crh.wxbase.common.entity.base.BaseModel;
import com.crh.wxbase.common.entity.QueryModel;
import com.crh.wxbase.common.entity.page.PageDto;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.common.constant.ColumnConsts;
import com.crh.wxbase.system.entity.SysUser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author rory.chen
 * @date 2021-01-22 14:39
 */
public class BaseService<M extends BaseMapper<T>, T extends BaseModel> extends ServiceImpl<M, T> {

    @Autowired
    private HttpServletRequest request;

    /**
     * 分页查询
     *
     * @param queryModel
     */
    public PageableItemsDto selectListByPage(QueryModel queryModel) {
        PageableItemsDto<T> itemsDto = new PageableItemsDto<>();
        //是否查询指定id数据
        if (Objects.nonNull(queryModel.getId())) {
            List<T> items = new ArrayList<>();
            items.add(super.getById(queryModel.getId()));
            itemsDto.setItems(items);
            return buildCodeAndMsg(itemsDto);
        }
        //是否查询指定id列表数据
        if (CollectionUtils.isNotEmpty(queryModel.getIds())) {
            itemsDto.setItems((List<T>) super.listByIds(queryModel.getIds()));
            return buildCodeAndMsg(itemsDto);
        }
        //封装查询信息
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(queryModel.getOrderField())) {
            queryWrapper.orderBy(true, queryModel.isAsc(),
                    com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(queryModel.getOrderField()));
        }
        // 统一主键ID倒排
        queryWrapper.orderByDesc(ColumnConsts.ID);
        if (StringUtils.isNotBlank(queryModel.getSearchValue())) {
            String[] searchKeyArray = queryModel.getSearchKey().split(",");
            queryWrapper.and(query -> {
                for (String key : searchKeyArray) {
                    query.like(key, queryModel.getSearchValue()).or();
                }
                return query;
            });
        }

        return selectPageSpecial(queryModel, queryWrapper);
    }


    /**
     * 分页内容
     *
     * @param queryModel
     * @param queryWrapper
     * @return
     */
    public PageableItemsDto<T> selectPageSpecial(QueryModel queryModel, QueryWrapper queryWrapper){
        PageableItemsDto<T> itemsDto = new PageableItemsDto<>();
        Page<T> page = new Page<>(queryModel.getPage(), queryModel.getPageSize());
        page(page, queryWrapper);
        PageDto pageDto = new PageDto();
        pageDto.setCurrentPage(page.getCurrent());
        pageDto.setPageSize(page.getSize());
        pageDto.setTotalRows(page.getTotal());
        pageDto.setTotalPages(page.getPages());
        itemsDto.setItems(page.getRecords());
        itemsDto.setPage(pageDto);
        return buildCodeAndMsg(itemsDto);
    }


    @Override
    public boolean save(T entity) {
        SysUser sysUser = (SysUser) request.getSession().getAttribute(CommonConsts.USERINFO);
        if (sysUser != null) {
            entity.setCreateId(sysUser.getId());
        }
        entity.setCreateDate(new Date());
        return super.save(entity);
    }

    @Override
    public boolean update(T entity, Wrapper<T> updateWrapper) {
        SysUser sysUser = (SysUser) request.getSession().getAttribute(CommonConsts.USERINFO);
        if (sysUser != null) {
            entity.setUpdateId(sysUser.getId());
        }
        entity.setUpdateDate(new Date());
        return super.update(entity, updateWrapper);
    }

    @Override
    public boolean updateById(T entity) {
        SysUser sysUser = (SysUser) request.getSession().getAttribute(CommonConsts.USERINFO);
        if (sysUser != null) {
            entity.setUpdateId(sysUser.getId());
        }
        entity.setUpdateDate(new Date());
        return super.updateById(entity);
    }

    private PageableItemsDto buildCodeAndMsg(PageableItemsDto itemsDto){
        itemsDto.setCode(ResponseCodeEnum.SUCCESS.getCode());
        itemsDto.setMsg(ResponseCodeEnum.SUCCESS.getMsg());
        return itemsDto;
    }

    private PageableItemsDto buildCodeAndMsg(PageableItemsDto itemsDto, ResponseCodeEnum codeEnum){
        itemsDto.setCode(codeEnum.getCode());
        itemsDto.setMsg(codeEnum.getMsg());
        return itemsDto;
    }

}
