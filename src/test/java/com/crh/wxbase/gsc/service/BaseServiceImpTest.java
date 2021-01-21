package com.crh.wxbase.gsc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crh.wxbase.gsc.entity.SysConfig;
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
public class BaseServiceImpTest {

    @Autowired
    private SysConfigService sysConfigService;

    @Test
    public void test() {
        Page<SysConfig> page = new Page<>(1, 10);
        QueryWrapper<SysConfig> queryWrapper = new QueryWrapper<>();
        sysConfigService.page(page, queryWrapper);
        System.out.println("总共条数："+page.getTotal());
    }
}
