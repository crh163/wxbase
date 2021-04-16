package com.crh.wxbase.gsc.controller;

import com.crh.wxbase.common.constant.CommonConsts;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.gsc.entity.dto.req.QueryAuthorByDynastyReq;
import com.crh.wxbase.gsc.entity.dto.req.QueryCollectionReq;
import com.crh.wxbase.gsc.service.GscCollectionService;
import com.crh.wxbase.system.entity.SysWxUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rory.chen
 * @date 2021-01-24 14:18
 */
@RestController
@Api(tags = "收藏")
@RequestMapping("/gscCollection")
public class GscCollectionController {

    @Autowired
    private GscCollectionService gscCollectionService;

    @Autowired
    private HttpServletRequest request;

    @ApiOperation("获取用户收藏夹")
    @PostMapping("/queryCollection")
    public PageableItemsDto queryCollection(@RequestBody QueryCollectionReq queryCollectionReq) {
        SysWxUser userInfo = (SysWxUser) request.getAttribute(CommonConsts.USERINFO);
        userInfo = new SysWxUser();
        userInfo.setOpenId("oPkYQ5PB0939moD3qTCLQB-vElz8");
        return gscCollectionService.queryCollection(queryCollectionReq, userInfo);
    }

}
