package com.crh.wxbase.common.entity.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jon.he
 * @date 2019-09-16 14:03
 */
@Data
@ApiModel("分页信息")
public class PageDto {

    @ApiModelProperty("当前页")
    Long currentPage;

    @ApiModelProperty("每页个数")
    Long pageSize;

    @ApiModelProperty("总页数")
    Long totalPages;

    @ApiModelProperty("总行数")
    Long totalRows;
}
