package com.crh.wxbase.gsc.service;

import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.gsc.entity.dto.req.QueryAuthorByDynastyReq;
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
public class GscAuthorServiceImpTest {

    @Autowired
    private GscAuthorService gscAuthorService;

    @Test
    public void test() {
        QueryAuthorByDynastyReq authorByDynasty = new QueryAuthorByDynastyReq();
        authorByDynasty.setDynastyId(15L);
        authorByDynasty.setPage(1);
        authorByDynasty.setPageSize(20);
        PageableItemsDto gscAuthorPoetries = gscAuthorService.queryAuthorPoetryByDynastyId(authorByDynasty);
        int i =0;
    }
}
