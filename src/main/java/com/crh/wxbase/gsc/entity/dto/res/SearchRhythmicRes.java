package com.crh.wxbase.gsc.entity.dto.res;

import lombok.Data;

/**
 * @author rory.chen
 * @date 2021-03-22 16:43
 */
@Data
public class SearchRhythmicRes {

    private Long authorId;

    private String authorName;

    private Long dynastyId;

    private String dynastyName;

    private Long rhythmicId;

    private String rhythmicName;

    private String paragraphsText;

}
