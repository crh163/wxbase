package com.crh.wxbase.gsc.controller;

import com.crh.wxbase.common.entity.QueryModel;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.common.entity.resp.Response;
import com.crh.wxbase.common.utils.ResponseUtil;
import com.crh.wxbase.gsc.entity.dto.RhythmicInfoDto;
import com.crh.wxbase.gsc.service.GscRhythmicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rory.chen
 * @date 2021-01-24 14:18
 */
@RestController
@Api(tags = "古诗词")
@RequestMapping("/gscRhythmic")
public class GscRhythmicController {

    @Autowired
    private GscRhythmicService gscRhythmicService;

    @ApiOperation("查询古诗词数据")
    @PostMapping("")
    public PageableItemsDto query(@RequestBody QueryModel queryModel) {
        return gscRhythmicService.selectListByPage(queryModel);
    }

    @ApiOperation("查询诗词详情数据")
    @PostMapping("queryInfoById")
    public Response queryInfoById(Long rhythmicId) {
        RhythmicInfoDto rhythmicInfoDto = gscRhythmicService.buildCompleteRhythmicById(rhythmicId);
        return ResponseUtil.getSuccess(rhythmicInfoDto);
    }

}
