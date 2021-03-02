package com.crh.wxbase.system.controller;

import com.crh.wxbase.common.config.exception.WxbaseException;
import com.crh.wxbase.common.constant.ResponseCodeEnum;
import com.crh.wxbase.common.entity.QueryModel;
import com.crh.wxbase.common.entity.page.PageableItemsDto;
import com.crh.wxbase.common.entity.resp.Response;
import com.crh.wxbase.common.utils.CollectionUtil;
import com.crh.wxbase.common.utils.ResponseUtil;
import com.crh.wxbase.system.entity.SysConfig;
import com.crh.wxbase.system.service.SysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @author rory.chen
 * @date 2021-01-18 14:18
 */
@RestController
@Api(tags = "系统配置")
@RequestMapping("/sysconfig")
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;

    @ApiOperation("查询系统配置")
    @PostMapping("")
    public PageableItemsDto query(@RequestBody QueryModel queryModel) {
        return sysConfigService.selectListByPage(queryModel);
    }

    @ApiOperation("系统配置新增")
    @PostMapping("/insert")
    public Response insert(@Valid SysConfig sysConfig){
        return ResponseUtil.getResult(sysConfigService.save(sysConfig));
    }

    @ApiOperation("系统配置修改")
    @PostMapping("/update")
    public Response update(SysConfig sysConfig) throws WxbaseException {
        if(Objects.isNull(sysConfig.getId())){
            throw new WxbaseException(ResponseCodeEnum.FAIL_ALL_UPDATE_NULL_ID);
        }
        return ResponseUtil.getResult(sysConfigService.updateById(sysConfig));
    }

    @ApiOperation("系统配置删除")
    @PostMapping("/deleteByIds")
    public Response deleteByIds(String ids) throws WxbaseException {
        List<Long> idList = CollectionUtil.converIdsToList(ids);
        for(Long id : idList){
            SysConfig user = sysConfigService.getById(id);
            if(Objects.isNull(user)){
                throw new WxbaseException(ResponseCodeEnum.FAIL_ALL_DELETE_PORTION_NULL_ID);
            }
        }
        sysConfigService.removeByIds(idList);
        return ResponseUtil.getSuccess();
    }

}
