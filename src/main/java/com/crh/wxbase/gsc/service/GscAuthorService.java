package com.crh.wxbase.gsc.service;

import com.crh.wxbase.common.service.BaseService;
import com.crh.wxbase.gsc.entity.dao.GscAuthorPoetry;
import com.crh.wxbase.gsc.entity.db.GscAuthor;
import com.crh.wxbase.gsc.entity.db.GscDynasty;
import com.crh.wxbase.gsc.entity.dto.pojo.AuthorPoetryDto;
import com.crh.wxbase.gsc.mapper.GscAuthorMapper;
import com.crh.wxbase.gsc.mapper.GscDynastyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rory.chen
 * @date 2021-01-21 18:06
 */
@Service
public class GscAuthorService extends BaseService<GscAuthorMapper, GscAuthor> {

    @Autowired
    private GscDynastyMapper gscDynastyMapper;

    @Autowired
    private GscAuthorMapper gscAuthorMapper;

    /**
     * 根据朝代查询所有诗人对应的诗句
     *
     * @param dynastyId
     * @return
     */
    public List<GscAuthorPoetry> queryAuthorPoetryByDynastyId(Long dynastyId){
        GscDynasty dynasty = gscDynastyMapper.selectById(dynastyId);

        return gscAuthorMapper.selectAuthorPoetryByDynastyId(dynastyId, "");
    }

}
