package com.crh.wxbase.common.entity.page;

import com.crh.wxbase.common.constant.ResponseCodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author rory.chen
 * @date 2021-01-07 14:39
 */
@Data
@NoArgsConstructor
public class PageableItemsDto<T> extends ItemsDto<T>{

    private Integer code;

    private String msg;

    public PageableItemsDto(ResponseCodeEnum responseCodeEnum){
        this.code = responseCodeEnum.getCode();
        this.msg = responseCodeEnum.getMsg();
    }

}
