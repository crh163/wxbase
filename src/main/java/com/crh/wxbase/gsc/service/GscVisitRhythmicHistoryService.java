package com.crh.wxbase.gsc.service;

import com.crh.wxbase.common.service.BaseService;
import com.crh.wxbase.gsc.entity.db.GscVisitRhythmicHistory;
import com.crh.wxbase.gsc.mapper.GscVisitRhythmicHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author rory.chen
 * @date 2021-01-21 18:06
 */
@Service
public class GscVisitRhythmicHistoryService extends BaseService<GscVisitRhythmicHistoryMapper, GscVisitRhythmicHistory> {

    @Autowired
    private GscVisitRhythmicHistoryMapper gscVisitRhythmicHistoryMapper;

}
