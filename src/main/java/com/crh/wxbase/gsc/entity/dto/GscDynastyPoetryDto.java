package com.crh.wxbase.gsc.entity.dto;

import com.crh.wxbase.gsc.entity.dto.pojo.AuthorPoetryDto;
import lombok.Data;

import java.util.List;

/**
 * @author rory.chen
 * @date 2021-03-03 16:29
 */
@Data
public class GscDynastyPoetryDto {

    /**
     * 朝代id
     */
    private Long dynastyId;

    /**
     * 朝代名称
     */
    private String dynastyName;

    /**
     * 作者信息
     */
    private List<AuthorPoetryDto> authorPoetrys;

}
