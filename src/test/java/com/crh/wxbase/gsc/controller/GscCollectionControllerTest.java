package com.crh.wxbase.gsc.controller;

import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.common.entity.resp.Response;
import com.crh.wxbase.gsc.entity.dto.req.QueryCollectionReq;
import com.crh.wxbase.system.controller.SysMenuController;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void testQuery() {
        QueryCollectionReq queryCollectionReq = new QueryCollectionReq();
        queryCollectionReq.setPage(1);
        queryCollectionReq.setPageSize(10);
        queryCollectionReq.setCollectType("0");
        queryCollectionReq.setParentFolderId("1");
        PageableItemsDto pageableItemsDto = gscCollectionController.queryCollection(queryCollectionReq);
        log.info(new Gson().toJson(pageableItemsDto));
    }

}
