package com.crh.wxbase.gsc.thread;

import com.crh.wxbase.common.entity.page.ItemsDto;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.gsc.entity.dto.req.SearchRhythmicReq;
import com.crh.wxbase.gsc.entity.dto.res.SearchRhythmicRes;
import com.crh.wxbase.gsc.service.GscAuthorService;
import com.crh.wxbase.gsc.service.GscParagraphsService;
import com.crh.wxbase.gsc.service.GscRhythmicService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author rory.chen
 * @date 2021-03-22 18:04
 */
@Slf4j
@Component
@NoArgsConstructor
@AllArgsConstructor
public class AppSearchParagraphsThread extends Thread {

    private GscParagraphsService gscParagraphsService;

    private SearchRhythmicReq searchRhythmicReq;

    private Map<String, ItemsDto> pageableItemMap;

    private CountDownLatch countDownLatch;

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        PageableItemsDto<SearchRhythmicRes> pageableItemsDto = gscParagraphsService.queryParagraphsToAppSearch(searchRhythmicReq);
        ItemsDto<SearchRhythmicRes> itemsDto = new ItemsDto<>();
        BeanUtils.copyProperties(pageableItemsDto, itemsDto);
        pageableItemMap.put("paragraphs", itemsDto);
        countDownLatch.countDown();
        log.info("AppSearchParagraphsThread end 耗时 :{}毫秒 ", (System.currentTimeMillis() - startTime));
    }
}
