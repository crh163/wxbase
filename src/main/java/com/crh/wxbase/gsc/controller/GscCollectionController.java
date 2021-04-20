package com.crh.wxbase.gsc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crh.wxbase.common.config.exception.WxbaseException;
import com.crh.wxbase.common.constant.ColumnConsts;
import com.crh.wxbase.common.constant.CommonConsts;
import com.crh.wxbase.common.constant.ResponseCodeEnum;
import com.crh.wxbase.common.constant.YesOrNoConsts;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.common.entity.resp.Response;
import com.crh.wxbase.common.utils.ResponseUtil;
import com.crh.wxbase.gsc.entity.db.GscCollection;
import com.crh.wxbase.gsc.entity.dto.req.collection.*;
import com.crh.wxbase.gsc.entity.dto.req.QueryCollectionReq;
import com.crh.wxbase.gsc.entity.dto.req.common.BaseIdReq;
import com.crh.wxbase.gsc.service.GscCollectionService;
import com.crh.wxbase.system.entity.SysWxUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rory.chen
 * @date 2021-01-24 14:18
 */
@RestController
@Api(tags = "收藏")
@RequestMapping("/api/gscCollection")
public class GscCollectionController {

    @Autowired
    private GscCollectionService gscCollectionService;

    @Autowired
    private HttpServletRequest request;

    @ApiOperation("获取用户收藏夹")
    @PostMapping("/queryCollection")
    public PageableItemsDto queryCollection(@RequestBody QueryCollectionReq queryCollectionReq)
            throws WxbaseException {
        return gscCollectionService.queryCollection(queryCollectionReq, getOpenId());
    }

    @ApiOperation("新增收藏夹")
    @PostMapping("/insert")
    public Response insert(@RequestBody InsertReq insertReq) throws WxbaseException {
        GscCollection gscCollection = new GscCollection();
        gscCollection.setOpenId(getOpenId());
        gscCollection.setName(insertReq.getName());
        gscCollection.setHasFolder(YesOrNoConsts.YES_STRING);
        gscCollectionService.save(gscCollection);
        return ResponseUtil.getSuccess();
    }

    @ApiOperation("修改收藏夹")
    @PostMapping("/update")
    public Response update(@RequestBody UpdateReq updateReq) throws WxbaseException {
        GscCollection gscCollection = new GscCollection();
        gscCollection.setName(updateReq.getName());
        gscCollectionService.update(gscCollection, new QueryWrapper<GscCollection>()
                .eq(ColumnConsts.OPENID, getOpenId())
                .eq(ColumnConsts.HAS_FOLDER, YesOrNoConsts.YES_STRING)
                .eq(ColumnConsts.ID, updateReq.getId()));
        return ResponseUtil.getSuccess();
    }

    @ApiOperation("删除收藏夹")
    @PostMapping("/delete")
    public Response delete(@RequestBody BaseIdReq deleteReq) throws WxbaseException {
        gscCollectionService.remove(new QueryWrapper<GscCollection>()
                .eq(ColumnConsts.OPENID, getOpenId())
                .eq(ColumnConsts.HAS_FOLDER, YesOrNoConsts.YES_STRING)
                .eq(ColumnConsts.ID, deleteReq.getId()));
        return ResponseUtil.getSuccess();
    }

    @ApiOperation("收藏诗词")
    @PostMapping("/collection")
    public Response collection(@RequestBody CollectionReq collectionReq) throws WxbaseException {
        GscCollection gscCollection = new GscCollection();
        gscCollection.setOpenId(getOpenId());
        gscCollection.setName(collectionReq.getName());
        gscCollection.setHasFolder(YesOrNoConsts.NO_STRING);
        gscCollection.setCollectId(collectionReq.getCollectId());
        gscCollection.setParentFolderId(collectionReq.getParentFolderId());
        gscCollectionService.save(gscCollection);
        return ResponseUtil.getSuccess();
    }

    @ApiOperation("取消收藏")
    @PostMapping("/cancelCollection")
    public Response cancelCollection(@RequestBody BaseIdReq cancelReq) throws WxbaseException {
        gscCollectionService.remove(new QueryWrapper<GscCollection>()
                .eq(ColumnConsts.OPENID, getOpenId())
                .eq(ColumnConsts.HAS_FOLDER, YesOrNoConsts.NO_STRING)
                .eq(ColumnConsts.ID, cancelReq.getId()));
        return ResponseUtil.getSuccess();
    }

    @ApiOperation("更换收藏夹")
    @PostMapping("/replaceCollection")
    public Response replaceCollection(@RequestBody ReplaceCollectionReq replaceReq) throws WxbaseException {
        GscCollection gscCollection = new GscCollection();
        gscCollection.setParentFolderId(replaceReq.getNewCollectionId());
        gscCollectionService.update(gscCollection, new QueryWrapper<GscCollection>()
                .eq(ColumnConsts.OPENID, getOpenId())
                .eq(ColumnConsts.HAS_FOLDER, YesOrNoConsts.NO_STRING)
                .eq(ColumnConsts.ID, replaceReq.getId()));
        return ResponseUtil.getSuccess();
    }

    /**
     * 获取openId
     *
     * @return
     * @throws WxbaseException
     */
    private String getOpenId() throws WxbaseException {
        SysWxUser userInfo = (SysWxUser) request.getAttribute(CommonConsts.USERINFO);
        if (userInfo == null) {
            throw new WxbaseException(ResponseCodeEnum.FAIL_USER_NO_LOGIN);
        }
        return userInfo.getOpenId();
    }

}
