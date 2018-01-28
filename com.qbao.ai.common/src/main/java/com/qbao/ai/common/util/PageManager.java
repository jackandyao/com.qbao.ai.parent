package com.qbao.ai.common.util;

/**
 * Created with IntelliJ IDEA.
 * To change this template use File | Settings | File Templates.
 */
public class PageManager {
    private Integer page;
    private Integer pageSize;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public PageManager() {
    }
    public PageManager(Integer page, Integer pageSize) {
        if (page != null && pageSize != null && pageSize > 0 && page > 0) {
            this.page = (page - 1) * pageSize;
            this.pageSize = pageSize;
        } else {
            this.page = 0;
            this.pageSize = 10;
        }
    }
}
