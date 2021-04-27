package com.crh.wxbase.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crh.wxbase.common.entity.gen.Columns;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author rory.chen
 * @date 2021-01-12 18:32
 */
public interface CommonMapper extends BaseMapper<Columns> {

    List<Columns> selectColumnsByTableName(@Param("tableName") String tableName);

}
