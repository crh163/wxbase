package com.crh.wxbase.gsc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crh.wxbase.system.entity.SysConfig;
import com.crh.wxbase.system.service.SysConfigService;
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
public class GscDynastyServiceImpTest {

    @Autowired
    private GscDynastyService gscDynastyService;

    @Test
    public void test() {
        gscDynastyService.queryAllDynasty();
    }
}
