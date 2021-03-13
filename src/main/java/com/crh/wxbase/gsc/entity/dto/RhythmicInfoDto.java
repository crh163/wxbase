package com.crh.wxbase.gsc.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class RhythmicInfoDto {

    /**
     * 诗id
     */
    private Long rhythmicId;

    /**
     * 标题
     */
    private String rhythmic;

    /**
     * 诗句数
     */
    private Integer rowNumber;

    /**
     * 诗词内容
     */
    private List<String> paragraphsTextList;

    /**
     * 作者
     */
    private GscAuthorDto author;

}
