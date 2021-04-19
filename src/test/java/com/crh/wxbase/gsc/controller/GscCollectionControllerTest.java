package com.crh.wxbase.gsc.controller;

import com.crh.wxbase.common.constant.CommonConsts;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.common.entity.resp.Response;
import com.crh.wxbase.gsc.entity.dto.req.QueryCollectionReq;
import com.crh.wxbase.system.controller.SysMenuController;
import com.crh.wxbase.system.entity.SysWxUser;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rory.chen
 * @date 2021-02-27 18:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GscCollectionControllerTest {

    @Autowired
    private GscCollectionController gscCollectionController;

    @Autowired
    private HttpServletRequest request;

    @Test
    public void testQuery() {
        QueryCollectionReq queryCollectionReq = new QueryCollectionReq();
        queryCollectionReq.setPage(1);
        queryCollectionReq.setPageSize(10);
//        queryCollectionReq.setParentFolderId("1");
        PageableItemsDto pageableItemsDto = gscCollectionController.queryCollection(queryCollectionReq);
        log.info(new Gson().toJson(pageableItemsDto));
    }

    @Before
    public void beforeTest(){
        SysWxUser userInfo = new SysWxUser();
        userInfo.setOpenId("oPkYQ5PB0939moD3qTCLQB-vElz8");
        request.setAttribute(CommonConsts.USERINFO, userInfo);
    }

}
