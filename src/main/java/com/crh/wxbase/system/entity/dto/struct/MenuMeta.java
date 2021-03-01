package com.crh.wxbase.system.entity.dto.struct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rory.chen
 * @date 2021-03-01 17:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuMeta {

    /**
     * 是否需要登录访问
     */
    private Boolean requireAuth;

}
