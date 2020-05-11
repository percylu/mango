package com.weehai.mango.core.service;

import com.weehai.mango.core.page.PageRequest;
import com.weehai.mango.core.page.PageResult;

import java.util.List;

/**
 * @author 卢水柏
 * @company WeeHai
 * @date 2019/12/25 3:53 下午
 **/
public interface CurdService<T> {

    /**
     * 分页查询
     * @param pageRequest 自定义,统一分页查询请求
     * @return PageResult 自定义,统一分页查询结果
     */
    PageResult findPage(PageRequest pageRequest);

}
