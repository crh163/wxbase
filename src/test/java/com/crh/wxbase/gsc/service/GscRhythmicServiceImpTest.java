package com.crh.wxbase.gsc.service;

import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.gsc.entity.dto.req.QueryRhythmicByAuthorReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author rory.chen
 * @date 2021-01-06 18:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GscRhythmicServiceImpTest {

    @Autowired
    private GscRhythmicService gscRhythmicService;

    @Test
    public void test() {
        QueryRhythmicByAuthorReq queryRhythmicByAuthorReq = new QueryRhythmicByAuthorReq();
        queryRhythmicByAuthorReq.setPage(1);
        queryRhythmicByAuthorReq.setPageSize(5);
        queryRhythmicByAuthorReq.setAuthorId(1L);
        PageableItemsDto pageableItemsDto = gscRhythmicService.queryRhythmicByAuthorId(queryRhythmicByAuthorReq);
        int i =0;
    }
}
