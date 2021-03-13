package com.crh.wxbase.gsc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crh.wxbase.common.constant.ColumnConsts;
import com.crh.wxbase.common.entity.QueryModel;
import com.crh.wxbase.common.entity.page.PageDto;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.common.service.BaseService;
import com.crh.wxbase.gsc.entity.dao.GscAuthorPoetry;
import com.crh.wxbase.gsc.entity.db.GscAuthor;
import com.crh.wxbase.gsc.entity.db.GscDynasty;
import com.crh.wxbase.gsc.entity.dto.QueryAuthorByDynasty;
import com.crh.wxbase.gsc.entity.dto.pojo.AuthorPoetryDto;
import com.crh.wxbase.gsc.mapper.GscAuthorMapper;
import com.crh.wxbase.gsc.mapper.GscDynastyMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rory.chen
 * @date 2021-01-21 18:06
 */
@Service
public class GscAuthorService extends BaseService<GscAuthorMapper, GscAuthor> {

    @Autowired
    private GscAuthorMapper gscAuthorMapper;

    /**
     * 根据朝代查询所有诗人对应的诗句
     *
     * @param authorByDynasty
     * @return
     */
    public PageableItemsDto queryAuthorPoetryByDynastyId(QueryAuthorByDynasty authorByDynasty){
        QueryModel queryModel = new QueryModel();
        queryModel.setPage(authorByDynasty.getPage());
        queryModel.setPageSize(authorByDynasty.getPageSize());
        PageableItemsDto<GscAuthor> itemsDto = selectPageSpecial(queryModel, new QueryWrapper<GscAuthor>()
                .eq(ColumnConsts.DYNASTY_ID, authorByDynasty.getDynastyId()));
        if(CollectionUtils.isEmpty(itemsDto.getItems())) {
            return itemsDto;
        }
        PageableItemsDto<GscAuthorPoetry> poetryItemsDto = new PageableItemsDto<>();
        BeanUtils.copyProperties(itemsDto, poetryItemsDto);
        List<Long> authorIds = itemsDto.getItems().stream().map(GscAuthor::getId).collect(Collectors.toList());
        StringBuilder idStrs = new StringBuilder();
        for(Long authorId : authorIds){
            idStrs.append(authorId).append(",");
        }
        String idStr = idStrs.toString();
        List<GscAuthorPoetry> gscAuthorPoetries = gscAuthorMapper.selectAuthorPoetryByDynastyId(
                idStr.substring(0, idStr.length() - 1));
        poetryItemsDto.setItems(gscAuthorPoetries);
        return poetryItemsDto;
    }

}
