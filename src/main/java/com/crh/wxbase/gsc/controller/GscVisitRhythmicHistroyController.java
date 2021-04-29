package com.crh.wxbase.gsc.controller;

import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.gsc.service.GscVisitRhythmicHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rory.chen
 * @date 2021-01-24 14:18
 */
@RestController
@Api(tags = "访问历史记录")
@RequestMapping("/gscVisitRhythmicHistroy")
public class GscVisitRhythmicHistroyController {

    @Autowired
    private GscVisitRhythmicHistoryService gscVisitRhythmicHistoryService;

    @ApiOperation("分页查询访问历史记录")
    @PostMapping("/query")
    public PageableItemsDto query() {
        return null;
    }

}
