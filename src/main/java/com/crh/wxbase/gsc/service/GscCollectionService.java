package com.crh.wxbase.gsc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crh.wxbase.common.constant.ColumnConsts;
import com.crh.wxbase.common.constant.CommonConsts;
import com.crh.wxbase.common.constant.YesOrNoConsts;
import com.crh.wxbase.common.entity.QueryModel;
import com.crh.wxbase.common.entity.page.PageDto;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.common.service.BaseService;
import com.crh.wxbase.gsc.entity.db.GscCollection;
import com.crh.wxbase.gsc.entity.dto.req.QueryCollectionReq;
import com.crh.wxbase.gsc.mapper.GscCollectionMapper;
import com.crh.wxbase.system.entity.SysWxUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;

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
                .orderByDesc(ColumnConsts.CREATE_DATE);
        if(StringUtils.isNotBlank(queryCollectionReq.getParentFolderId())){
            queryWrapper.eq(ColumnConsts.HAS_FOLDER, YesOrNoConsts.NO_INT);
            queryWrapper.eq(ColumnConsts.PARENT_FOLDER_ID, queryCollectionReq.getParentFolderId());
            return selectPageSpecial(queryModel, queryWrapper);
        }
        queryWrapper.eq(ColumnConsts.HAS_FOLDER, YesOrNoConsts.YES_INT);
        PageableItemsDto<GscCollection> itemsDto = selectPageSpecial(queryModel, queryWrapper);
        boolean firstQuery = CommonConsts.ZERO_INT.equals(queryCollectionReq.getPage()) ||
                CommonConsts.ONE_INT.equals(queryCollectionReq.getPage());
        if(firstQuery && CollectionUtils.isEmpty(itemsDto.getItems())){
            GscCollection collection = insertFirstFolder(userInfo.getOpenId());
            itemsDto.setItems(Arrays.asList(collection));
            PageDto pageDto = new PageDto();
            pageDto.setTotalPages(1L);
            pageDto.setTotalRows(1L);
            itemsDto.setPage(pageDto);
        }
        return itemsDto;
    }

    /**
     * 添加默认文件夹
     *
     * @param openId
     * @return
     */
    public GscCollection insertFirstFolder(String openId){
        GscCollection collection = new GscCollection();
        collection.setHasFolder(CommonConsts.ONE_STR);
        collection.setName(CommonConsts.DEFAULT_FLODER);
        collection.setOpenId(openId);
        save(collection);
        return collection;
    }

}
