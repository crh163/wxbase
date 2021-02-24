package com.crh.wxbase.system.entity;

import com.crh.wxbase.common.entity.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author rory.chen
 * @date 2021-02-24 17:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysUser extends BaseModel {

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
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

}
