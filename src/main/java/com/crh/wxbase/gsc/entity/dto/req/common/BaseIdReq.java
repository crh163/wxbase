package com.crh.wxbase.gsc.entity.dto.req.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author rory.chen
 * @date 2021/4/20
 */
@Data
public class BaseIdReq {

    @ApiModelProperty("id")
    @NotBlank(message = "id不能为空")
    private Long id;

}
