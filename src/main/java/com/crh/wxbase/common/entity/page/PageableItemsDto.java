package com.crh.wxbase.common.entity.page;

import lombok.Data;

import java.util.List;

/**
 * @author jon.he
 * @date 2019-09-16 14:59
 */
@Data
public class PageableItemsDto<T> {

    private List<T> items;

    private PageDto page;

}
