package com.crh.wxbase.gsc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crh.wxbase.common.constant.ColumnConsts;
import com.crh.wxbase.common.entity.resp.Response;
import com.crh.wxbase.common.service.BaseService;
import com.crh.wxbase.common.utils.ResponseUtil;
import com.crh.wxbase.gsc.entity.db.GscAuthor;
import com.crh.wxbase.gsc.entity.db.GscParagraphs;
import com.crh.wxbase.gsc.entity.db.GscRhythmic;
import com.crh.wxbase.gsc.entity.dto.RhythmicInfoDto;
import com.crh.wxbase.gsc.mapper.GscRhythmicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 查询诗词详情数据
     *
     * @param rhythmicId
     * @return
     */
    public Response queryInfoById(Long rhythmicId) {
        GscRhythmic rhythmic = getById(rhythmicId);
        //作者
        GscAuthor author = gscAuthorService.getById(rhythmic.getAuthorId());
        //诗词内容
        QueryWrapper<GscParagraphs> wrapper = new QueryWrapper<>();
        wrapper.eq(ColumnConsts.RHYTHMIC_ID, rhythmic.getId());
        List<GscParagraphs> paragraphsList = gscParagraphsService.list(wrapper);
        //封装业务数据
        RhythmicInfoDto rhythmicInfoDto = new RhythmicInfoDto();
        rhythmicInfoDto.setRhythmic(rhythmic.getRhythmic());
        rhythmicInfoDto.setAuthor(author);
        rhythmicInfoDto.setParagraphsList(paragraphsList);
        return ResponseUtil.getSuccess(rhythmicInfoDto);
    }

}
