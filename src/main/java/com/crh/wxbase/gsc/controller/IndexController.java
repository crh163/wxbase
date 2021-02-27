package com.crh.wxbase.gsc.controller;

import com.crh.wxbase.common.constant.CommonConsts;
import com.crh.wxbase.common.entity.resp.Response;
import com.crh.wxbase.common.utils.ResponseUtil;
import com.crh.wxbase.gsc.constant.RedisKeyConstant;
import com.crh.wxbase.gsc.entity.dto.RhythmicInfoDto;
import com.crh.wxbase.gsc.service.GscRhythmicService;
import com.crh.wxbase.system.entity.SysUser;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@RestController
@Api(tags = "首页")
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private GscRhythmicService gscRhythmicService;

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

}
