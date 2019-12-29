package com.weehai.mango.core.page;

import java.util.List;

/**
 * @author 卢水柏
 * @company WeeHai
 * @date 2019/12/25 4:04 下午
 **/
public class PageResult {
    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * 记录总数
     */
    private long totalSize;

    /**
     * 页码总数
     */
    private int totalPages;

    /**
     * 分页数据
     */
    private List<?> content;

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<?> getContent() {
        return content;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }
}
