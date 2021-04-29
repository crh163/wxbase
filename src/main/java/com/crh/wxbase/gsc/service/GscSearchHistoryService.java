package com.crh.wxbase.gsc.service;

import com.crh.wxbase.common.service.BaseService;
import com.crh.wxbase.gsc.entity.db.GscSearchHistory;
import com.crh.wxbase.gsc.mapper.GscSearchHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author rory.chen
 * @date 2021-01-21 18:06
 */
@Service
public class GscSearchHistoryService extends BaseService<GscSearchHistoryMapper, GscSearchHistory> {

    @Autowired
    private GscSearchHistoryMapper gscSearchHistoryMapper;

}
