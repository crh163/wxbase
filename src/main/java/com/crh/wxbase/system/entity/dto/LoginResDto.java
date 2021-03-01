package com.crh.wxbase.system.entity.dto;

import com.crh.wxbase.system.entity.SysUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author rory.chen
 * @date 2021-02-24 17:14
 */
@Data
@NoArgsConstructor
public class LoginResDto {

    /**
     * x-access-token
     */
    private String token;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户的性别
     */
    private String gender;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户等级id
     */
    private Integer levelId;

    public LoginResDto(String token, SysUser sysUser) {
        this.token = token;
        if (Objects.nonNull(sysUser)) {
            this.username = sysUser.getUsername();
            this.gender = sysUser.getGender();
            this.phone = sysUser.getPhone();
            this.levelId = sysUser.getLevelId();
        }
    }

}
