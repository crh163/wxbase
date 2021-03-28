package com.crh.wxbase.gsc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crh.wxbase.gsc.entity.dao.GscAuthorPoetry;
import com.crh.wxbase.gsc.entity.db.GscAuthor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author rory.chen
 * @date 2021-01-12 18:32
 */
public interface GscAuthorMapper extends BaseMapper<GscAuthor> {

    /**
     * 查询所有朝代的信息，10个作者对应的诗
     *
     * @param dynIds
     * @return
     */
    List<GscAuthorPoetry> selectDynastyAuthorPoetry(@Param("dynIds") List<Long> dynIds, @Param("queryAuthorNumber") Integer queryAuthorNumber);

    /**
     * 根据朝代查询所有诗人对应的诗句
     *
     * @param authorIds
     * @return
     */
    List<GscAuthorPoetry> selectAuthorPoetryByDynastyId(@Param("authorIds") String authorIds);

}
