package com.crh.wxbase.gsc.controller;

import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.gsc.entity.dto.QueryAuthorByDynasty;
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
     * 根据朝代查询所有诗人对应的诗句
     *
     * @param queryAuthorByDynasty
     * @return
     */
    @ApiOperation("热门诗人")
    @PostMapping("/queryAuthorByDynasty")
    public PageableItemsDto query(@RequestBody QueryAuthorByDynasty queryAuthorByDynasty) {
        return null;
    }

}
