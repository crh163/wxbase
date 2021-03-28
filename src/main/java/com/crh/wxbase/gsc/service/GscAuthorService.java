package com.crh.wxbase.gsc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crh.wxbase.common.constant.ColumnConsts;
import com.crh.wxbase.common.entity.QueryModel;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.common.service.BaseService;
import com.crh.wxbase.gsc.entity.dao.GscAuthorPoetry;
import com.crh.wxbase.gsc.entity.db.GscAuthor;
import com.crh.wxbase.gsc.entity.db.GscDynasty;
import com.crh.wxbase.gsc.entity.dto.req.QueryAuthorByDynastyReq;
import com.crh.wxbase.gsc.entity.dto.req.SearchRhythmicReq;
import com.crh.wxbase.gsc.entity.dto.res.SearchRhythmicRes;
import com.crh.wxbase.gsc.mapper.GscAuthorMapper;
import com.crh.wxbase.gsc.mapper.GscDynastyMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author rory.chen
 * @date 2021-01-21 18:06
 */
@Service
public class GscAuthorService extends BaseService<GscAuthorMapper, GscAuthor> {

    @Autowired
    private GscAuthorMapper gscAuthorMapper;

    @Autowired
    private GscDynastyMapper gscDynastyMapper;

    /**
     * 根据朝代查询所有诗人对应的诗数
     *
     * @param authorByDynasty
     * @return
     */
    public PageableItemsDto queryAuthorPoetryByDynastyId(QueryAuthorByDynastyReq authorByDynasty){
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


    /**
     * app究极搜索
     *
     * @param searchRhythmicReq
     * @return
     */
    public List<SearchRhythmicRes> queryAuthorToAppSearch(SearchRhythmicReq searchRhythmicReq){
        int page = searchRhythmicReq.getPage() == 0 ? 1 : searchRhythmicReq.getPage();
        int pageSize = (page - 1) * searchRhythmicReq.getPageSize();
        List<GscAuthor> list = list(new QueryWrapper<GscAuthor>().like(ColumnConsts.NAME, searchRhythmicReq.getSearchText())
                .last(" LIMIT " + pageSize + "," + searchRhythmicReq.getPageSize()));
        List<SearchRhythmicRes> searchRhythmicList = new ArrayList<>();
        Map<Long, String> dynastyMap = gscDynastyMapper.selectList(new QueryWrapper<>())
                .stream().collect(Collectors.toMap(GscDynasty::getId, GscDynasty::getName));
        for(GscAuthor author : list){
            SearchRhythmicRes searchRhythmicRes = new SearchRhythmicRes();
            searchRhythmicRes.setAuthorId(author.getId());
            searchRhythmicRes.setAuthorName(author.getName());
            searchRhythmicRes.setDynastyName(dynastyMap.get(author.getDynastyId()));
            searchRhythmicList.add(searchRhythmicRes);
        }
        return searchRhythmicList;
    }

}
