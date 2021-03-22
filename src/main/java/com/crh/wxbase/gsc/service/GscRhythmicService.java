package com.crh.wxbase.gsc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crh.wxbase.common.constant.ColumnConsts;
import com.crh.wxbase.common.entity.QueryModel;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.common.service.BaseService;
import com.crh.wxbase.gsc.constant.DynastyConstant;
import com.crh.wxbase.gsc.entity.db.GscAuthor;
import com.crh.wxbase.gsc.entity.db.GscDynasty;
import com.crh.wxbase.gsc.entity.db.GscParagraphs;
import com.crh.wxbase.gsc.entity.db.GscRhythmic;
import com.crh.wxbase.gsc.entity.dto.GscAuthorDto;
import com.crh.wxbase.gsc.entity.dto.req.QueryRhythmicByAuthorReq;
import com.crh.wxbase.gsc.entity.dto.RhythmicInfoDto;
import com.crh.wxbase.gsc.entity.dto.req.SearchRhythmicReq;
import com.crh.wxbase.gsc.entity.dto.res.SearchRhythmicRes;
import com.crh.wxbase.gsc.mapper.GscAuthorMapper;
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
public class GscRhythmicService extends BaseService<GscRhythmicMapper, GscRhythmic> {

    @Autowired
    private GscParagraphsService gscParagraphsService;

    @Autowired
    private GscAuthorService gscAuthorService;

    @Autowired
    private GscAuthorMapper gscAuthorMapper;

    @Autowired
    private GscRhythmicMapper gscRhythmicMapper;

    @Autowired
    private GscDynastyService gscDynastyService;

    /**
     * 封装诗词详情数据
     *
     * @param rhythmicId
     * @return
     */
    public RhythmicInfoDto buildCompleteRhythmicById(Long rhythmicId){
        GscRhythmic rhythmic = getById(rhythmicId);
        return buildCompleteRhythmicById(rhythmic);
    }


    /**
     * 封装诗词详情数据
     *
     * @param rhythmic
     * @return
     */
    public RhythmicInfoDto buildCompleteRhythmicById(GscRhythmic rhythmic){
        //作者
        GscAuthor author = gscAuthorService.getById(rhythmic.getAuthorId());
        //作者朝代
        GscDynasty dynasty = gscDynastyService.getById(author.getDynastyId());
        //诗词内容
        QueryWrapper<GscParagraphs> wrapper = new QueryWrapper<>();
        wrapper.eq(ColumnConsts.RHYTHMIC_ID, rhythmic.getId());
        List<GscParagraphs> paragraphsList = gscParagraphsService.list(wrapper);
        //封装业务数据
        RhythmicInfoDto rhythmicInfoDto = new RhythmicInfoDto();
        rhythmicInfoDto.setRhythmicId(rhythmic.getId());
        rhythmicInfoDto.setRhythmic(rhythmic.getRhythmic());
        rhythmicInfoDto.setRowNumber(rhythmic.getRowNumber());
        GscAuthorDto gscAuthorDto = new GscAuthorDto();
        gscAuthorDto.setAuthorName(author.getName());
        gscAuthorDto.setDescription(author.getDescription());
        gscAuthorDto.setDynastyName(dynasty.getName());
        rhythmicInfoDto.setAuthor(gscAuthorDto);
        rhythmicInfoDto.setParagraphsTextList(paragraphsList.stream()
                .map(GscParagraphs::getText).collect(Collectors.toList()));
        return rhythmicInfoDto;
    }


    /**
     * 随机获取db 4言唐诗
     *
     * @return
     */
    public RhythmicInfoDto queryTodayRandomRhythmic() {
        Integer randomNum = gscRhythmicMapper.queryTodayRandomNum(DynastyConstant.ID_TANG);
        //随机查询到的唐朝诗词id
        GscRhythmic gscRhythmic = gscRhythmicMapper.queryTodayRandomRhythmic(DynastyConstant.ID_TANG, randomNum);
        //获取诗的完整数据
        return buildCompleteRhythmicById(gscRhythmic);
    }


    /**
     * 根据诗人分页查询古诗词
     *
     * @param queryRhythmicByAuthorReq
     * @return
     */
    public PageableItemsDto queryRhythmicByAuthorId(QueryRhythmicByAuthorReq queryRhythmicByAuthorReq) {
        QueryModel queryModel = new QueryModel();
        queryModel.setPage(queryRhythmicByAuthorReq.getPage());
        queryModel.setPageSize(queryRhythmicByAuthorReq.getPageSize());
        PageableItemsDto<GscRhythmic> itemsDto = selectPageSpecial(queryModel, new QueryWrapper<GscRhythmic>()
                .eq(ColumnConsts.AUTHOR_ID, queryRhythmicByAuthorReq.getAuthorId()));
        if(CollectionUtils.isEmpty(itemsDto.getItems())) {
            return itemsDto;
        }
        PageableItemsDto<RhythmicInfoDto> rhythmicItemsDto = new PageableItemsDto<>();
        BeanUtils.copyProperties(itemsDto, rhythmicItemsDto);
        List<Long> thythmicIds = itemsDto.getItems().stream().map(GscRhythmic::getId).collect(Collectors.toList());
        List<RhythmicInfoDto> rhythmicInfoDtoList = new ArrayList<>();
        for(Long thythmicId : thythmicIds){
            rhythmicInfoDtoList.add(buildCompleteRhythmicById(thythmicId));
        }
        rhythmicItemsDto.setItems(rhythmicInfoDtoList);
        return rhythmicItemsDto;
    }


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
        PageableItemsDto<GscRhythmic> itemsDto = selectPageSpecial(queryModel, new QueryWrapper<GscRhythmic>()
                .like(ColumnConsts.RHYTHMIC, searchRhythmicReq.getSearchText()));
        if(CollectionUtils.isEmpty(itemsDto.getItems())) {
            return itemsDto;
        }
        PageableItemsDto<SearchRhythmicRes> poetryItemsDto = new PageableItemsDto<>();
        BeanUtils.copyProperties(itemsDto, poetryItemsDto);
        List<SearchRhythmicRes> searchRhythmicList = new ArrayList<>();

        Set<Long> authorIds = itemsDto.getItems().stream().map(GscRhythmic::getAuthorId).collect(Collectors.toSet());
        Map<Long, String> authorMap = gscAuthorMapper.selectBatchIds(authorIds)
                .stream().collect(Collectors.toMap(GscAuthor::getId, GscAuthor::getName));
        for(GscRhythmic rhythmic : itemsDto.getItems()){
            SearchRhythmicRes searchRhythmicRes = new SearchRhythmicRes();
            searchRhythmicRes.setRhythmicId(rhythmic.getId());
            searchRhythmicRes.setRhythmicName(rhythmic.getRhythmic());
            searchRhythmicRes.setAuthorName(authorMap.get(rhythmic.getAuthorId()));
            searchRhythmicList.add(searchRhythmicRes);
        }
        poetryItemsDto.setItems(searchRhythmicList);
        return poetryItemsDto;
    }


}
