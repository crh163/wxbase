package com.crh.wxbase.common.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crh.wxbase.common.entity.QueryModel;
import com.crh.wxbase.common.entity.page.PageDto;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.common.constant.ColumnConsts;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author rory.chen
 * @date 2021-01-22 14:39
 */
public class BaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

    /**
     * 分页查询
     *
     * @param queryModel
     */
    public PageableItemsDto selectListByPage(QueryModel queryModel){
        PageableItemsDto<T> itemsDto = new PageableItemsDto<>();
        //是否查询指定id数据
        if (CollectionUtils.isNotEmpty(queryModel.getIds())) {
            itemsDto.setItems((List<T>) super.listByIds(queryModel.getIds()));
            return itemsDto;
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

        Page<T> page = new Page<>(queryModel.getPage(), queryModel.getPageSize());
        page(page, queryWrapper);
        PageDto pageDto = new PageDto();
        pageDto.setCurrentPage(page.getCurrent());
        pageDto.setPageSize(page.getSize());
        pageDto.setTotalRows(page.getTotal());
        pageDto.setTotalPages(page.getPages());
        itemsDto.setItems(page.getRecords());
        itemsDto.setPage(pageDto);
        return itemsDto;
    }

}
