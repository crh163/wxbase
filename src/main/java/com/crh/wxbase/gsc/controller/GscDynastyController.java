package com.crh.wxbase.gsc.controller;

import com.crh.wxbase.common.entity.QueryModel;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.common.entity.resp.Response;
import com.crh.wxbase.common.utils.ResponseUtil;
import com.crh.wxbase.gsc.service.GscDynastyService;
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
@Api(tags = "诗词朝代")
@RequestMapping("/gscDynasty")
public class GscDynastyController {

    @Autowired
    private GscDynastyService gscDynastyService;

    @ApiOperation("查询所有朝代")
    @PostMapping("queryAllDynasty")
    public Response queryAllDynasty() {
        gscDynastyService.queryAllDynasty();
        return ResponseUtil.getSuccess();
    }

}
