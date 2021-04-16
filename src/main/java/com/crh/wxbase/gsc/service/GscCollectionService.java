package com.crh.wxbase.gsc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crh.wxbase.common.constant.ColumnConsts;
import com.crh.wxbase.common.constant.YesOrNoConsts;
import com.crh.wxbase.common.entity.QueryModel;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.common.service.BaseService;
import com.crh.wxbase.gsc.entity.db.GscCollection;
import com.crh.wxbase.gsc.entity.db.GscRhythmic;
import com.crh.wxbase.gsc.entity.dto.RhythmicInfoDto;
import com.crh.wxbase.gsc.entity.dto.req.QueryCollectionReq;
import com.crh.wxbase.gsc.entity.dto.req.QueryRhythmicByAuthorReq;
import com.crh.wxbase.gsc.mapper.GscCollectionMapper;
import com.crh.wxbase.system.entity.SysWxUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rory.chen
 * @date 2021-01-21 18:06
 */
@Service
public class GscCollectionService extends BaseService<GscCollectionMapper, GscCollection> {

    @Autowired
    private GscCollectionMapper gscCollectionMapper;

    /**
     * 分页查询收藏
     *
     * @param queryCollectionReq
     * @param userInfo
     * @return
     */
    public PageableItemsDto queryCollection(QueryCollectionReq queryCollectionReq, SysWxUser userInfo) {
        QueryModel queryModel = new QueryModel();
        queryModel.setPage(queryCollectionReq.getPage());
        queryModel.setPageSize(queryCollectionReq.getPageSize());
        QueryWrapper<GscCollection> queryWrapper = new QueryWrapper<GscCollection>()
                .eq(ColumnConsts.OPENID, userInfo.getOpenId())
                .eq(ColumnConsts.COLLECT_TYPE, queryCollectionReq.getCollectType());
        if (StringUtils.isNotBlank(queryCollectionReq.getParentFolderId())) {
            queryWrapper.eq(ColumnConsts.HAS_FOLDER, YesOrNoConsts.NO_INT);
            queryWrapper.eq(ColumnConsts.PARENT_FOLDER_ID, queryCollectionReq.getParentFolderId());
        } else {
            queryWrapper.eq(ColumnConsts.HAS_FOLDER, YesOrNoConsts.YES_INT);
        }
        queryWrapper.orderByDesc(ColumnConsts.CREATE_DATE);
        return selectPageSpecial(queryModel, queryWrapper);
    }

}
