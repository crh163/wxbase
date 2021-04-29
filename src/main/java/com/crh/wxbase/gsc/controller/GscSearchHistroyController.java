package com.crh.wxbase.gsc.controller;

import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.gsc.service.GscSearchHistoryService;
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
@Api(tags = "搜索记录")
@RequestMapping("/gscAuthor")
public class GscSearchHistroyController {

    @Autowired
    private GscSearchHistoryService gscSearchHistoryService;

    @ApiOperation("分页查询搜索记录")
    @PostMapping("/query")
    public PageableItemsDto query() {
        return null;
    }

}
