package com.crh.wxbase.gsc.service;

import com.crh.wxbase.common.constant.YesOrNoConsts;
import com.crh.wxbase.common.service.BaseService;
import com.crh.wxbase.gsc.entity.dao.GscAuthorPoetry;
import com.crh.wxbase.gsc.entity.db.GscDynasty;
import com.crh.wxbase.gsc.entity.dto.GscDynastyPoetryDto;
import com.crh.wxbase.gsc.entity.dto.pojo.AuthorPoetry;
import com.crh.wxbase.gsc.entity.dto.req.QueryAllDynastyReq;
import com.crh.wxbase.gsc.mapper.GscAuthorMapper;
import com.crh.wxbase.gsc.mapper.GscDynastyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rory.chen
 * @date 2021-01-21 18:06
 */
@Service
public class GscDynastyService extends BaseService<GscDynastyMapper, GscDynasty> {

    @Autowired
    private GscAuthorMapper gscAuthorMapper;

    /**
     * 查询所有朝代的信息，10个作者对应的诗
     *
     * @return
     */
    public List<GscDynastyPoetryDto> queryAllDynasty(QueryAllDynastyReq queryAllDynastyReq) {
        List<GscDynasty> gscDynasties = list();
        List<GscDynastyPoetryDto> gscDynastyPoetryDtos = new ArrayList<>();
        if (!YesOrNoConsts.YES_INT.equals(queryAllDynastyReq.getIsQueryAuthor())) {
            return queryAuthorByDynasty(gscDynasties, queryAllDynastyReq.getQueryAuthorNumber());
        } else {
            for (GscDynasty gscDynasty : gscDynasties) {
                GscDynastyPoetryDto gscDynastyPoetryDto = new GscDynastyPoetryDto();
                gscDynastyPoetryDto.setDynastyId(gscDynasty.getId());
                gscDynastyPoetryDto.setDynastyName(gscDynasty.getName());
                gscDynastyPoetryDtos.add(gscDynastyPoetryDto);
            }
            return gscDynastyPoetryDtos;
        }
    }


    /**
     * 根据朝代集合查询对应诗人信息
     *
     * @param gscDynasties
     * @param queryAuthorNumber
     * @return
     */
    private List<GscDynastyPoetryDto> queryAuthorByDynasty(List<GscDynasty> gscDynasties, Integer queryAuthorNumber) {
        if(queryAuthorNumber==null){
            queryAuthorNumber = 10;
        }
        List<GscDynastyPoetryDto> gscDynastyPoetryDtos = new ArrayList<>();
        List<Long> dynIds = gscDynasties.stream().map(GscDynasty::getId).collect(Collectors.toList());
        List<GscAuthorPoetry> authorPoetrys = gscAuthorMapper.selectDynastyAuthorPoetry(dynIds, queryAuthorNumber);
        for (GscDynasty gscDynasty : gscDynasties) {
            List<AuthorPoetry> authorPoetries = new ArrayList<>();
            for (GscAuthorPoetry gscAuthorPoetry : authorPoetrys) {
                if (!gscDynasty.getId().equals(gscAuthorPoetry.getDynastyId())) {
                    continue;
                }
                AuthorPoetry authorPoetry = new AuthorPoetry();
                authorPoetry.setAuthorId(gscAuthorPoetry.getAuthorId());
                authorPoetry.setAuthorName(gscAuthorPoetry.getAuthorName());
                authorPoetry.setPoetryNumber(gscAuthorPoetry.getPoetryNumber());
                authorPoetries.add(authorPoetry);
            }
            GscDynastyPoetryDto gscDynastyPoetryDto = new GscDynastyPoetryDto();
            gscDynastyPoetryDto.setDynastyId(gscDynasty.getId());
            gscDynastyPoetryDto.setDynastyName(gscDynasty.getName());
            gscDynastyPoetryDto.setAuthorPoetrys(authorPoetries);
            gscDynastyPoetryDtos.add(gscDynastyPoetryDto);
        }
        return gscDynastyPoetryDtos;
    }

}
