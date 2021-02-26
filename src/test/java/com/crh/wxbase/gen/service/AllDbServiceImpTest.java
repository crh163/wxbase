package com.crh.wxbase.gen.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crh.wxbase.gen.dto.GenCiDto;
import com.crh.wxbase.gsc.entity.db.GscAuthor;
import com.crh.wxbase.gsc.entity.db.GscParagraphs;
import com.crh.wxbase.gsc.entity.db.GscRhythmic;
import com.crh.wxbase.gsc.entity.db.GscDynasty;
import com.crh.wxbase.gsc.service.GscAuthorService;
import com.crh.wxbase.gsc.service.GscParagraphsService;
import com.crh.wxbase.gsc.service.GscRhythmicService;
import com.crh.wxbase.gsc.service.GscDynastyService;
import com.csvreader.CsvReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author rory.chen
 * @date 2021-01-06 18:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AllDbServiceImpTest {

    @Autowired
    private GscAuthorService gscAuthorService;
    @Autowired
    private GscParagraphsService gscParagraphsService;
    @Autowired
    private GscRhythmicService gscRhythmicService;
    @Autowired
    private GscDynastyService gscDynastyService;

    //文件夹路径
    private static final String FILEPATH = "E:\\github\\poetry\\Poetry";

    @Test
    public void test() throws IOException {
        List<File> fileList = new ArrayList<>();
        getAllFile(FILEPATH, fileList);
//        insertDynasty(fileList);
        insertCiData(fileList);
    }

    /**
     * 新增诗词数据
     *
     * @param fileList
     */
    private void insertCiData(List<File> fileList) throws IOException {
        if (CollectionUtils.isEmpty(fileList)) {
            System.out.println("无诗词csv文件！");
            return;
        }
        Map<String, Long> dynastyMap = gscDynastyService.list().stream()
                .collect(Collectors.toMap(GscDynasty::getName, GscDynasty::getId));
        for (int i = 0; i < fileList.size(); i++) {
            File file = fileList.get(i);
            CsvReader csvReader = new CsvReader(file.getAbsolutePath(), ',', Charset.forName("UTF-8"));
            csvReader.readHeaders();
            String[] headArray = csvReader.getHeaders();
            System.out.println("开始保存第" + (i + 1) + "个文件，文件名: " + file.getName());

            int number = 0;
            List<GscParagraphs> gscParagraphsList = new ArrayList<>();
            while (csvReader.readRecord()) {
                List<String> texts = getParags(csvReader.get(headArray[3]));
                Long dynastyId = dynastyMap.get(csvReader.get(headArray[1]));
                number++;
                if (dynastyId == null) {
                    System.out.println("未查询到朝代信息：" + csvReader.get(headArray[1])+"，作者：" + csvReader.get(headArray[2]));
                    continue;
                }

                QueryWrapper<GscAuthor> wrapper = new QueryWrapper<>();
                wrapper.eq("name", csvReader.get(headArray[2]))
                        .eq("dynastyId", dynastyId);
                GscAuthor gscAuthor = gscAuthorService.getOne(wrapper);
                if(gscAuthor == null){
                    gscAuthor = new GscAuthor();
                    gscAuthor.setCreateId((long) 1);
                    gscAuthor.setCreateDate(new Date());
                    gscAuthor.setName(csvReader.get(headArray[2]));
                    gscAuthor.setDynastyId(dynastyId);
                    gscAuthorService.save(gscAuthor);
                }

                GscRhythmic gscRhythmic = new GscRhythmic();
                gscRhythmic.setCreateId((long) 1);
                gscRhythmic.setCreateDate(new Date());
                gscRhythmic.setDynastyId(dynastyId);
                gscRhythmic.setAuthorId(gscAuthor.getId());
                gscRhythmic.setRhythmic(csvReader.get(headArray[0]));
                gscRhythmic.setRowNumber(texts.size());
                gscRhythmicService.save(gscRhythmic);

                int n = 1;
                for (String text : texts) {
                    GscParagraphs paragraphs = new GscParagraphs();
                    paragraphs.setCreateId((long) 1);
                    paragraphs.setCreateDate(new Date());
                    paragraphs.setRhythmicId(gscRhythmic.getId());
                    paragraphs.setText(text);
                    paragraphs.setParOrder(n);
                    gscParagraphsList.add(paragraphs);
                    n++;
                }

            }
            System.out.println("开始批量保存诗词内容");
            gscParagraphsService.saveBatch(gscParagraphsList);
            System.out.println("第" + (i + 1) + "个文件的诗词csv数据新增完毕！共总 " + number + " 首诗词");
        }
        System.out.println("所有诗词csv数据新增完毕！");
    }


    private List<String> getParags(String text) {
        List<String> ss = new ArrayList<>();
        String[] split = text.split("。");
        for (String s : split) {
            String[] split1 = (s + "。").split("，");
            for (String s1 : split1) {
                if (!s1.endsWith("。")) {
                    s1 = s1 + "，";
                }
                ss.add(s1);
            }
        }
        return ss;
    }


    /**
     * 获取所有符合的文件
     *
     * @param path
     * @param fileList
     */
    public void getAllFile(String path, List<File> fileList) {
        File file = new File(path);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            //判断如果是.csv结尾的文件  存入集合
            if (tempList[i].isFile() && tempList[i].getName().endsWith(".csv")) {
                fileList.add(tempList[i]);
            }
            //如果是文件夹  递归调用
            if (tempList[i].isDirectory()) {
                getAllFile(tempList[i].getAbsolutePath(), fileList);
            }
        }
    }

    private void insertDynasty(List<File> fileList) {
        if (CollectionUtils.isEmpty(fileList)) {
            System.out.println("无诗词csv文件！");
            return;
        }
        Set<String> dyns = new HashSet<>();
        for (int i = 0; i < fileList.size(); i++) {
            File file = fileList.get(i);
            String name = file.getName().replace(".csv", "");
            if (name.contains("xxxxx")) {
                name = name.substring(0, name.indexOf("xxxxx"));
            }
            if (name.endsWith("朝") && !"南北朝".equals(name)) {
                name = name.substring(0, name.length() - 1);
            }
            dyns.add(name);
        }
        for (String dyn : dyns) {
            GscDynasty dynasty = new GscDynasty();
            dynasty.setName(dyn);
            dynasty.setCreateId((long) 1);
            gscDynastyService.save(dynasty);
        }
    }

}
