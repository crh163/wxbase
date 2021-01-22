package com.crh.wxbase.gen.service;

import com.alibaba.fastjson.JSONObject;
import com.crh.wxbase.gen.dto.GenCiDto;
import com.crh.wxbase.system.service.SysConfigService;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author rory.chen
 * @date 2021-01-06 18:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonDbServiceImpTest {

    private static final String FILEPATH = "E:\\github\\json";

    @Test
    public void test() throws IOException {
        List<File> fileList = new ArrayList<>();
        getAllFile(FILEPATH, fileList);

        for (int i = 0; i < fileList.size(); i++) {
            File file = fileList.get(i);
            //读取json文件内容
            String jsonText = FileUtils.readFileToString(file, "UTF-8");
            //转移成对象
            List<GenCiDto> rhyList = new Gson().fromJson(jsonText, List.class);
            for (GenCiDto genCi : rhyList) {

            }
        }
    }

    public void getAllFile(String path, List<File> fileList) {
        File file = new File(path);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            //判断如果是文件  存入集合
            if (tempList[i].isFile()) {
                fileList.add(tempList[i]);
            }
            //如果是文件夹  递归调用
            if (tempList[i].isDirectory()) {
                getAllFile(tempList[i].getAbsolutePath(), fileList);
            }
        }
    }

}
