package com.crh.wxbase.gsc.controller;

import com.crh.wxbase.common.entity.resp.Response;
import com.crh.wxbase.system.controller.SysMenuController;
import com.crh.wxbase.system.controller.SysUserController;
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
public class SystemControllerTest {

    @Autowired
    private SysMenuController sysMenuController;

    @Test
    public void testQueryMenu() {
        Response response = sysMenuController.queryMenu("admin");
        log.info(new Gson().toJson(response));
    }

}
