package com.crh.wxbase.system.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author rory.chen
 * @date 2021-02-24 17:14
 */
@Data
public class LoginReqDto {

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 密码明文
     */
    @ApiModelProperty("密码明文")
    private String password;

}
