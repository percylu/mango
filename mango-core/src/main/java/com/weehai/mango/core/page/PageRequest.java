package com.weehai.mango.core.page;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 卢水柏
 * @company WeeHai
 * @date 2019/12/25 4:00 下午
 **/
public class PageRequest {
    /**
     * 当前页码
     */
    private int pageNum = 1;

    /**
     * 每页数量
     */
    private int pageSize=10;

    /**
     * 查询参数
     */
    private Map<String,Object> params=new HashMap<>();

    public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
