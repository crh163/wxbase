package com.crh.wxbase.gsc.entity.dto;

import com.crh.wxbase.gsc.entity.db.GscAuthor;
import com.crh.wxbase.gsc.entity.db.GscParagraphs;
import lombok.Data;

import java.util.List;

@Data
public class RhythmicInfoDto {

    /**
     * 标题
     */
    private String rhythmic;

    /**
     * 诗词内容
     */
    private List<String> paragraphsTextList;

    /**
     * 作者
     */
    private GscAuthorDto author;

}
