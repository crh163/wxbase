package com.crh.wxbase.gsc.controller;

import com.crh.wxbase.common.entity.resp.Response;
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
public class IndexControllerTest {

    @Autowired
    private IndexController indexController;

    @Test
    public void testQueryToday() {
        Response response = indexController.queryToday();
        log.info(new Gson().toJson(response));
    }

}
