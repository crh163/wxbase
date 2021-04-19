package com.crh.wxbase.gsc.entity.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author rory.chen
 * @date 2021-03-04 15:40
 */
@Data
public class QueryCollectionReq {

    @ApiModelProperty("页码")
    private Integer page;

    @ApiModelProperty("每页个数")
    private Integer pageSize;

    @ApiModelProperty("收藏夹id（点击某个收藏夹时传，不传则查询收藏夹）")
    private String parentFolderId;

}
