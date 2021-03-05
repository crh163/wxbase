package com.crh.wxbase.gsc.service;

import com.crh.wxbase.common.service.BaseService;
import com.crh.wxbase.gsc.entity.dao.GscAuthorPoetry;
import com.crh.wxbase.gsc.entity.db.GscAuthor;
import com.crh.wxbase.gsc.entity.db.GscDynasty;
import com.crh.wxbase.gsc.entity.dto.GscDynastyPoetryDto;
import com.crh.wxbase.gsc.entity.dto.pojo.AuthorPoetryDto;
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
    public List<GscDynastyPoetryDto> queryAllDynasty() {
        List<GscDynasty> gscDynasties = list();
        List<Long> dynIds = gscDynasties.stream().map(GscDynasty::getId).collect(Collectors.toList());
        List<GscAuthorPoetry> authorPoetrys = gscAuthorMapper.selectDynastyAuthorPoetry(dynIds);
        List<GscDynastyPoetryDto> gscDynastyPoetryDtos = new ArrayList<>();
        for(GscDynasty gscDynasty : gscDynasties){
            List<AuthorPoetryDto> authorPoetryDtos = new ArrayList<>();
            for(GscAuthorPoetry gscAuthorPoetry : authorPoetrys){
                if(!gscDynasty.getId().equals(gscAuthorPoetry.getDynastyId())){
                    continue;
                }
                AuthorPoetryDto authorPoetryDto = new AuthorPoetryDto();
                authorPoetryDto.setAuthorId(gscAuthorPoetry.getAuthorId());
                authorPoetryDto.setAuthorName(gscAuthorPoetry.getAuthorName());
                authorPoetryDto.setPoetryNumber(gscAuthorPoetry.getPoetryNumber());
                authorPoetryDtos.add(authorPoetryDto);
            }
            GscDynastyPoetryDto gscDynastyPoetryDto = new GscDynastyPoetryDto();
            gscDynastyPoetryDto.setDynastyId(gscDynasty.getId());
            gscDynastyPoetryDto.setDynastyName(gscDynasty.getName());
            gscDynastyPoetryDto.setAuthorPoetrys(authorPoetryDtos);
            gscDynastyPoetryDtos.add(gscDynastyPoetryDto);
        }
        return gscDynastyPoetryDtos;
    }

}
