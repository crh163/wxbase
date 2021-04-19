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
import com.crh.wxbase.gsc.entity.dto.req.collection.CollectionReq;
import com.crh.wxbase.gsc.entity.dto.req.collection.DeleteReq;
import com.crh.wxbase.gsc.entity.dto.req.collection.InsertReq;
import com.crh.wxbase.gsc.entity.dto.req.QueryCollectionReq;
import com.crh.wxbase.gsc.entity.dto.req.collection.UpdateReq;
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
@RequestMapping("/gscCollection")
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
        gscCollection.setId(updateReq.getId());
        gscCollection.setName(updateReq.getName());
        gscCollectionService.update(gscCollection, new QueryWrapper<GscCollection>()
                .eq(ColumnConsts.OPENID, getOpenId())
                .eq(ColumnConsts.ID, updateReq.getId()));
        return ResponseUtil.getSuccess();
    }

    @ApiOperation("删除收藏夹")
    @PostMapping("/delete")
    public Response delete(@RequestBody DeleteReq deleteReq) throws WxbaseException {
        gscCollectionService.remove(new QueryWrapper<GscCollection>()
                .eq(ColumnConsts.OPENID, getOpenId())
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
