package com.crh.wxbase.common.entity.page;

import lombok.Data;

import java.util.List;

/**
 * @author rory.chen
 * @date 2021-01-07 14:39
 */
@Data
public class PageableItemsDto<T> {

    private List<T> items;

    private PageDto page;

}
