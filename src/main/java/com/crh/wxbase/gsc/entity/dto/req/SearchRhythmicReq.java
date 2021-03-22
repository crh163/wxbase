package com.crh.wxbase.gsc.entity.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author rory.chen
 * @date 2021-03-22 14:40
 */
@Data
public class SearchRhythmicReq {

    @ApiModelProperty("页码")
    private Integer page;

    @ApiModelProperty("每页个数")
    private Integer pageSize;

    @ApiModelProperty("查询类型")
    private Integer searchType;

    @ApiModelProperty("查询内容")
    private String searchText;

}
