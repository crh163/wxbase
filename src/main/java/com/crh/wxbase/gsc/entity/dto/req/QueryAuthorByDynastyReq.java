package com.crh.wxbase.gsc.entity.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author rory.chen
 * @date 2021-03-04 15:40
 */
@Data
public class QueryAuthorByDynastyReq {

    @ApiModelProperty("页码")
    private Integer page;

    @ApiModelProperty("每页个数")
    private Integer pageSize;

    @ApiModelProperty("朝代Id")
    private Long dynastyId;

}
