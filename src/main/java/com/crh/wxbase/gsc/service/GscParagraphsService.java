package com.crh.wxbase.gsc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crh.wxbase.common.constant.ColumnConsts;
import com.crh.wxbase.common.entity.QueryModel;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.common.service.BaseService;
import com.crh.wxbase.gsc.entity.db.GscAuthor;
import com.crh.wxbase.gsc.entity.db.GscDynasty;
import com.crh.wxbase.gsc.entity.db.GscParagraphs;
import com.crh.wxbase.gsc.entity.db.GscRhythmic;
import com.crh.wxbase.gsc.entity.dto.req.SearchRhythmicReq;
import com.crh.wxbase.gsc.entity.dto.res.SearchRhythmicRes;
import com.crh.wxbase.gsc.mapper.GscParagraphsMapper;
import com.crh.wxbase.gsc.mapper.GscRhythmicMapper;
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
public class GscParagraphsService extends BaseService<GscParagraphsMapper, GscParagraphs> {

    @Autowired
    private GscRhythmicMapper gscRhythmicMapper;

    /**
     * app究极搜索
     *
     * @param searchRhythmicReq
     * @return
     */
    public PageableItemsDto queryAuthorToAppSearch(SearchRhythmicReq searchRhythmicReq){
        QueryModel queryModel = new QueryModel();
        queryModel.setPage(searchRhythmicReq.getPage());
        queryModel.setPageSize(searchRhythmicReq.getPageSize());
        PageableItemsDto<GscParagraphs> itemsDto = selectPageSpecial(queryModel, new QueryWrapper<GscParagraphs>()
                .like(ColumnConsts.TEXT, searchRhythmicReq.getSearchText()));
        if(CollectionUtils.isEmpty(itemsDto.getItems())) {
            return itemsDto;
        }
        PageableItemsDto<SearchRhythmicRes> poetryItemsDto = new PageableItemsDto<>();
        BeanUtils.copyProperties(itemsDto, poetryItemsDto);
        List<SearchRhythmicRes> searchRhythmicList = new ArrayList<>();

        Set<Long> rhyIds = itemsDto.getItems().stream().map(GscParagraphs::getRhythmicId).collect(Collectors.toSet());
        Map<Long, String> rhythmicMap = gscRhythmicMapper.selectBatchIds(rhyIds)
                .stream().collect(Collectors.toMap(GscRhythmic::getId, GscRhythmic::getRhythmic));
        for(GscParagraphs paragraphs : itemsDto.getItems()){
            SearchRhythmicRes searchRhythmicRes = new SearchRhythmicRes();
            searchRhythmicRes.setParagraphsText(paragraphs.getText());
            searchRhythmicRes.setRhythmicId(paragraphs.getRhythmicId());
            searchRhythmicRes.setRhythmicName(rhythmicMap.get(paragraphs.getRhythmicId()));
            searchRhythmicList.add(searchRhythmicRes);
        }
        poetryItemsDto.setItems(searchRhythmicList);
        return poetryItemsDto;
    }


}
