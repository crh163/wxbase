package com.crh.wxbase.gsc.entity.dto.pojo;

import lombok.Data;

/**
 * @author rory.chen
 * @date 2021-03-03 16:31
 */
@Data
public class AuthorPoetry {

    /**
     * 作者id
     */
    private Long authorId;

    /**
     * 作者名称
     */
    private String authorName;

    /**
     * 有多少首诗词
     */
    private Integer poetryNumber;

}
