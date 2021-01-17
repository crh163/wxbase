package com.crh.wxbase.gsc.service;

import com.crh.wxbase.gsc.entity.SysConfig;
import com.crh.wxbase.gsc.mapper.SysConfigMapper;
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
    private SysConfigMapper sysConfigMapper;

    @Test
    public void test() {
        SysConfig sysConfig = sysConfigMapper.selectById(2);
        System.out.println(sysConfig.getPropName());
    }
}
