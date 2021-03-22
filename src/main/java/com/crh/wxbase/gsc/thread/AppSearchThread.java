package com.crh.wxbase.gsc.thread;

import com.crh.wxbase.gsc.service.GscAuthorService;
import com.crh.wxbase.gsc.service.GscParagraphsService;
import com.crh.wxbase.gsc.service.GscRhythmicService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @author rory.chen
 * @date 2021-03-22 18:04
 */
@Component
@NoArgsConstructor
public class AppSearchThread extends Thread {

    @Autowired
    private GscAuthorService gscAuthorService;

    @Autowired
    private GscRhythmicService gscRhythmicService;

    @Autowired
    private GscParagraphsService gscParagraphsService;

    private CountDownLatch countDownLatch;

    public AppSearchThread(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

    }
}
