package com.crh.wxbase.system.controller;

import com.crh.wxbase.common.entity.resp.Response;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SysWxUserControllerTest {

    @Autowired
    private SysWxUserController sysWxUserController;

    @Test
    public void testQueryToday() {
//        Response response = sysWxUserController.login("091VN20001gvvL10AB0005fnzF4VN204");
//        log.info(new Gson().toJson(response));
    }

}
