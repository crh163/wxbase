package com.crh.wxbase.gsc.controller;

import com.crh.wxbase.common.constant.CommonConsts;
import com.crh.wxbase.common.constant.ResponseCodeEnum;
import com.crh.wxbase.common.entity.page.ItemsDto;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.common.entity.resp.Response;
import com.crh.wxbase.common.utils.ResponseUtil;
import com.crh.wxbase.gsc.constant.AppSearchEnum;
import com.crh.wxbase.gsc.constant.RedisKeyConstant;
import com.crh.wxbase.gsc.entity.dto.RhythmicInfoDto;
import com.crh.wxbase.gsc.entity.dto.req.SearchRhythmicReq;
import com.crh.wxbase.gsc.entity.dto.res.SearchPageableItemsRes;
import com.crh.wxbase.gsc.entity.dto.res.SearchRhythmicRes;
import com.crh.wxbase.gsc.service.GscAuthorService;
import com.crh.wxbase.gsc.service.GscParagraphsService;
import com.crh.wxbase.gsc.service.GscRhythmicService;
import com.crh.wxbase.gsc.thread.AppSearchAuthorThread;
import com.crh.wxbase.gsc.thread.AppSearchParagraphsThread;
import com.crh.wxbase.gsc.thread.AppSearchRhythmicThread;
import com.google.gson.Gson;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

@RestController
@Api(tags = "首页")
@RequestMapping("/index")
@Slf4j
public class IndexController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private GscAuthorService gscAuthorService;

    @Autowired
    private GscRhythmicService gscRhythmicService;

    @Autowired
    private GscParagraphsService gscParagraphsService;

    @Autowired
    private Gson gson;

    @ApiOperation("每日一首")
    @PostMapping("/queryToday")
    public Response queryToday() {
        //先去redis中查询，如果没有今日推荐则插入 TODO 后续改成热门唐诗
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String todayDate = LocalDate.now().format(CommonConsts.DTF_DAY);
        String recommend = opsForValue.get(RedisKeyConstant.INDEX_TODAY_RECOMMEND_PREFIX + todayDate);
        RhythmicInfoDto rhythmicInfoDto = null;
        if (StringUtils.isBlank(recommend)) {
            rhythmicInfoDto = gscRhythmicService.queryTodayRandomRhythmic();
            String rhythmicInfoStr = gson.toJson(rhythmicInfoDto);
            opsForValue.set(RedisKeyConstant.INDEX_TODAY_RECOMMEND_PREFIX + todayDate,
                    rhythmicInfoStr, 1, TimeUnit.DAYS);
        } else {
            rhythmicInfoDto = gson.fromJson(recommend, RhythmicInfoDto.class);
        }
        return ResponseUtil.getSuccess(rhythmicInfoDto);
    }


    @ApiOperation("究极无敌牛逼搜索")
    @PostMapping("/search")
    public Object search(@RequestBody SearchRhythmicReq searchRhythmicReq) throws InterruptedException {
        AppSearchEnum appSearchEnum = AppSearchEnum.getEnumByCode(searchRhythmicReq.getSearchType());
        if (Objects.isNull(appSearchEnum)) {
            return new PageableItemsDto(ResponseCodeEnum.FAIL_INDEX_NULL_SEARCHTYPE);
        }
        switch (appSearchEnum) {
            case AUTHOR:
                return gscAuthorService.queryAuthorToAppSearch(searchRhythmicReq);
            case RHYTHMIC:
                return gscRhythmicService.queryRhythmicToAppSearch(searchRhythmicReq);
            case PARAGRAPHS:
                return gscParagraphsService.queryParagraphsToAppSearch(searchRhythmicReq);
            case ALL:
                return searchAllToThread(searchRhythmicReq);
            default:
                return new PageableItemsDto(ResponseCodeEnum.FAIL_INDEX_NULL_SEARCHTYPE);
        }
    }


    private SearchPageableItemsRes searchAllToThread(SearchRhythmicReq searchRhythmicReq) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Map<String, ItemsDto> pageableItemMap = new HashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {
            executorService.execute(new AppSearchParagraphsThread(gscParagraphsService, searchRhythmicReq, pageableItemMap, countDownLatch));
            executorService.execute(new AppSearchAuthorThread(gscAuthorService, searchRhythmicReq, pageableItemMap, countDownLatch));
            executorService.execute(new AppSearchRhythmicThread(gscRhythmicService, searchRhythmicReq, pageableItemMap, countDownLatch));
            countDownLatch.await();
        } catch (Exception e){
            log.error("搜索线程执行异常", e);
        } finally {
            executorService.shutdown();
        }

        SearchPageableItemsRes searchPageableItemsRes = new SearchPageableItemsRes();
        searchPageableItemsRes.setCode(ResponseCodeEnum.SUCCESS.getCode());
        searchPageableItemsRes.setMsg(ResponseCodeEnum.SUCCESS.getMsg());
        searchPageableItemsRes.setPageableItemMap(pageableItemMap);
        return searchPageableItemsRes;
    }

}
