package com.crh.wxbase.system.entity;

import com.crh.wxbase.common.entity.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author rory.chen
 * @date 2021-02-24 17:10
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysUser extends BaseModel implements Serializable {

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 登录密码
     */
    @ApiModelProperty("登录密码")
    @NotBlank(message = "登录密码不能为空")
    private String password;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    @ApiModelProperty("用户的性别，值为1时是男性，值为2时是女性，值为0时是未知")
    private String gender;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 用户等级id
     */
    @ApiModelProperty(value = "用户等级", example = "1")
    private Integer levelId;

}
