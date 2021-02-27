package com.crh.wxbase.gsc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crh.wxbase.gsc.entity.db.GscRhythmic;
import org.apache.ibatis.annotations.Param;

/**
 * @author rory.chen
 * @date 2021-01-12 18:32
 */
public interface GscRhythmicMapper extends BaseMapper<GscRhythmic> {

    /**
     * 获取db 4言唐诗随机数
     * @param dynastyId
     * @return
     */
    Integer queryTodayRandomNum(Long dynastyId);

    /**
     * 获取db 随机4言唐诗
     * @param dynastyId
     * @param randomNum
     * @return
     */
    GscRhythmic queryTodayRandomRhythmic(@Param("dynastyId") Long dynastyId, @Param("randomNum") Integer randomNum);

}
