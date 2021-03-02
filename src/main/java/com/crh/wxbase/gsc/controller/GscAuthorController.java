package com.crh.wxbase.gsc.controller;

import com.crh.wxbase.common.entity.QueryModel;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.gsc.service.GscAuthorService;
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
@Api(tags = "诗词作者")
@RequestMapping("/gscAuthor")
public class GscAuthorController {

    @Autowired
    private GscAuthorService gscAuthorService;

    /**
     * TODO 暂未使用
     *
     * @param queryModel
     * @return
     */
    @ApiOperation("热门诗人")
    @PostMapping("")
    public PageableItemsDto query(@RequestBody QueryModel queryModel) {
        return gscAuthorService.selectListByPage(queryModel);
    }

}
