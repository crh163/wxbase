package com.crh.wxbase.gsc.entity.dto.req;

import lombok.Data;

@Data
public class QueryAllDynastyReq {

    /**
     * 查询作者的个数
     */
    private Integer queryAuthorNumber;

    private Integer isQueryAuthor;

}
