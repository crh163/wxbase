package com.crh.wxbase.gsc.entity.dto.res;

import com.crh.wxbase.common.constant.ResponseCodeEnum;
import com.crh.wxbase.common.entity.page.ItemsDto;
import com.crh.wxbase.common.entity.page.PageDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author rory.chen
 * @date 2021-01-07 14:39
 */
@Data
@NoArgsConstructor
public class SearchPageableItemsRes<T> {

    private Integer code;

    private String msg;

    private Map pageableItemMap;

}
