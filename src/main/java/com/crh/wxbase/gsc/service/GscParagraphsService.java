package com.crh.wxbase.gsc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crh.wxbase.common.constant.ColumnConsts;
import com.crh.wxbase.common.service.BaseService;
import com.crh.wxbase.gsc.entity.db.GscParagraphs;
import com.crh.wxbase.gsc.entity.db.GscRhythmic;
import com.crh.wxbase.gsc.entity.dto.req.SearchRhythmicReq;
import com.crh.wxbase.gsc.entity.dto.res.SearchRhythmicRes;
import com.crh.wxbase.gsc.mapper.GscParagraphsMapper;
import com.crh.wxbase.gsc.mapper.GscRhythmicMapper;
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
    public List<SearchRhythmicRes> queryParagraphsToAppSearch(SearchRhythmicReq searchRhythmicReq) {
        int page = searchRhythmicReq.getPage() == 0 ? 1 : searchRhythmicReq.getPage();
        int pageSize = (page - 1) * searchRhythmicReq.getPageSize();
        QueryWrapper<GscParagraphs> wrapper = new QueryWrapper<>();
        if (searchRhythmicReq.getSearchText().length() > 1) {
            wrapper.last(" where MATCH (text) AGAINST ('" + searchRhythmicReq.getSearchText()
                    + "' IN NATURAL LANGUAGE MODE)");
        } else {
            wrapper.like(ColumnConsts.TEXT, searchRhythmicReq.getSearchText());
        }
        wrapper.last(" LIMIT " + pageSize + "," + searchRhythmicReq.getPageSize());
        List<GscParagraphs> list = list(wrapper);
        List<SearchRhythmicRes> searchRhythmicList = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)){
            return searchRhythmicList;
        }
        Set<Long> rhyIds = list.stream().map(GscParagraphs::getRhythmicId).collect(Collectors.toSet());
        Map<Long, String> rhythmicMap = gscRhythmicMapper.selectBatchIds(rhyIds)
                .stream().collect(Collectors.toMap(GscRhythmic::getId, GscRhythmic::getRhythmic));
        for (GscParagraphs paragraphs : list) {
            SearchRhythmicRes searchRhythmicRes = new SearchRhythmicRes();
            searchRhythmicRes.setParagraphsText(paragraphs.getText());
            searchRhythmicRes.setRhythmicId(paragraphs.getRhythmicId());
            searchRhythmicRes.setRhythmicName(rhythmicMap.get(paragraphs.getRhythmicId()));
            searchRhythmicList.add(searchRhythmicRes);
        }
        return searchRhythmicList;
    }


}
