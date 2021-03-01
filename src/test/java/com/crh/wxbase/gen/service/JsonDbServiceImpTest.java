//package com.crh.wxbase.gen.service;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.crh.wxbase.gen.dto.GenCiDto;
//import com.crh.wxbase.gsc.entity.db.GscAuthor;
//import com.crh.wxbase.gsc.entity.db.GscParagraphs;
//import com.crh.wxbase.gsc.entity.db.GscRhythmic;
//import com.crh.wxbase.gsc.entity.db.GscDynasty;
//import com.crh.wxbase.gsc.service.GscAuthorService;
//import com.crh.wxbase.gsc.service.GscParagraphsService;
//import com.crh.wxbase.gsc.service.GscRhythmicService;
//import com.crh.wxbase.gsc.service.GscDynastyService;
//import com.google.gson.FieldNamingPolicy;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//import org.apache.commons.io.FileUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.util.CollectionUtils;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.*;
//import java.util.stream.Collectors;
//
//
///**
// * @author rory.chen
// * @date 2021-01-06 18:02
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class JsonDbServiceImpTest {
//
//    @Autowired
//    private GscAuthorService gscAuthorService;
//    @Autowired
//    private GscParagraphsService gscParagraphsService;
//    @Autowired
//    private GscRhythmicService gscRhythmicService;
//    @Autowired
//    private GscDynastyService gscDynastyService;
//
//    //文件夹路径
//    private static final String FILEPATH = "E:\\gsc\\chinese-poetry-master\\ci";
//    //作者文件名
//    private static final String AUTHORFILE = "author.song.json";
//
//    @Test
//    public void test() throws IOException {
//        List<File> fileList = new ArrayList<>();
//        File authorFile = new File(FILEPATH + "\\" + AUTHORFILE);
////        getAllFile(FILEPATH, fileList);
//        //新增类型
//        String typeName = "全宋词";
//        String typeRemark = "《全宋词》是中国近百年来最重要的古籍整理成果之一。宋词和唐诗均为中国古典诗的艺术高峰。" +
//                "清代所编《全唐诗》是家喻户晓籍，现又新编出《全宋词》，堪称中国文学的双璧。全书共五册，荟萃宋代三百年间的词作。";
//        Long typeId = insertGscType(typeName, typeRemark);
//        //新增作者数据
////        insertAuthor(authorFile);
//        //新增诗词数据
//        insertCiData(fileList, typeId);
//    }
//
//
//    /**
//     * 新增类型
//     *
//     * @param typeName
//     * @param typeRemark
//     */
//    private Long insertGscType(String typeName, String typeRemark) {
//        GscDynasty dbType = gscDynastyService.getOne(new QueryWrapper<GscDynasty>().eq("name", typeName));
//        if (Objects.isNull(dbType)) {
//            GscDynasty type = new GscDynasty();
//            type.setName(typeName);
//            type.setRemark(typeRemark);
//            gscDynastyService.save(type);
//            System.out.println("新增type【" + typeName + "】数据成功！");
//            return type.getId();
//        } else {
//            System.out.println("已存在type为【" + typeName + "】数据，无需新增！");
//            return dbType.getId();
//        }
//    }
//
//
//    /**
//     * 新增诗词数据
//     *
//     * @param fileList
//     * @param typeId
//     */
//    private void insertCiData(List<File> fileList, Long typeId) throws IOException {
//        if (CollectionUtils.isEmpty(fileList)) {
//            System.out.println("无诗词json文件！");
//            return;
//        }
//        for (int i = 0; i < fileList.size(); i++) {
//            File file = fileList.get(i);
//            //读取json文件内容
//            String jsonText = FileUtils.readFileToString(file, "UTF-8");
//            //转移成诗词对象
//            List<GenCiDto> rhyList = new Gson().fromJson(jsonText,
//                    new TypeToken<List<GenCiDto>>() {
//                    }.getType());
//            System.out.println("开始保存第" + (i + 1) + "个文件，总共" + rhyList.size() + "条数据");
//
//            List<String> names = rhyList.stream().map(GenCiDto::getAuthor).collect(Collectors.toList());
//            List<GscAuthor> authors = gscAuthorService.list(
//                    new QueryWrapper<GscAuthor>().in("name", names));
//            Map<String, GscAuthor> collect = authors.stream().collect(Collectors.toMap(GscAuthor::getName, User -> User));
//            List<GscParagraphs> gscParagraphsList = new ArrayList<>();
//            System.out.println("开始保存标题");
//            for (GenCiDto genCi : rhyList) {
//                GscAuthor gscAuthor = collect.get(genCi.getAuthor());
//                //未查询到作者直接跳过
//                if (Objects.isNull(gscAuthor)) {
//                    System.out.println("未查询到作者：" + genCi.getAuthor() + "，不保存数据！");
//                    continue;
//                }
//                GscRhythmic gscRhythmic = new GscRhythmic();
//                gscRhythmic.setAuthorId(gscAuthor.getId());
////                gscRhythmic.setTypeId(typeId);
//                gscRhythmic.setRhythmic(genCi.getRhythmic());
//                gscRhythmicService.save(gscRhythmic);
//                for (int j = 0; j < genCi.getParagraphs().size(); j++) {
//                    GscParagraphs gscParagraphs = new GscParagraphs();
//                    gscParagraphs.setRhythmicId(gscRhythmic.getId());
//                    gscParagraphs.setText(genCi.getParagraphs().get(j));
//                    gscParagraphs.setParOrder(j + 1);
//                    gscParagraphs.setCreateDate(new Date());
//                    gscParagraphsList.add(gscParagraphs);
//                }
//            }
//            System.out.println("开始批量保存");
//            gscParagraphsService.saveBatch(gscParagraphsList);
//            System.out.println("第" + (i + 1) + "个文件的诗词json数据新增完毕！");
//        }
//        System.out.println("所有诗词json数据新增完毕！");
//    }
//
//
//    /**
//     * 新增作者数据
//     *
//     * @param authorFile
//     */
//    private void insertAuthor(File authorFile) throws IOException {
//        if (Objects.isNull(authorFile)) {
//            System.out.println("无作者json文件！");
//            return;
//        }
//        //读取json文件内容
//        String jsonText = FileUtils.readFileToString(authorFile, "UTF-8");
//        //转移成作者对象
//        Gson gson = new GsonBuilder()
//                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//                .create();
//        List<GscAuthor> authors = gson.fromJson(jsonText,
//                new TypeToken<List<GscAuthor>>() {
//                }.getType());
//        List<GscAuthor> addAuthors = new ArrayList<>();
//        Set<String> authNames = new HashSet<>();
//        for (GscAuthor author : authors) {
//            if (author.getName().length() > 1 && !authNames.contains(author.getName())) {
//                author.setCreateDate(new Date());
//                addAuthors.add(author);
//                authNames.add(author.getName());
//            }
//        }
//        gscAuthorService.saveBatch(addAuthors);
//        System.out.println("作者json数据新增成功！个数为" + addAuthors.size());
//    }
//
//
//    /**
//     * 获取所有符合的文件
//     *
//     * @param path
//     * @param fileList
//     */
//    public void getAllFile(String path, List<File> fileList) {
//        File file = new File(path);
//        File[] tempList = file.listFiles();
//        for (int i = 0; i < tempList.length; i++) {
//            //判断如果是.json结尾的文件  存入集合
//            if (tempList[i].isFile() && tempList[i].getName().endsWith(".json")
//                    && !tempList[i].getName().endsWith(AUTHORFILE)) {
//                fileList.add(tempList[i]);
//            }
//            //如果是文件夹  递归调用
//            if (tempList[i].isDirectory()) {
//                getAllFile(tempList[i].getAbsolutePath(), fileList);
//            }
//        }
//    }
//
//}
