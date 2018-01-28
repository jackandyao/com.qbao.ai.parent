package com.qbao.ai.model.html;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

/**
 * @author wangping
 * @createTime 2017/4/17 上午11:28
 * $$LastChangedDate: 2017-04-17 14:51:49 +0800 (Mon, 17 Apr 2017) $$
 * $$LastChangedRevision: 128 $$
 * $$LastChangedBy: wangping $$
 */
public class SearchResult {
    //总的搜索结果数
    private int total;
    //第几页
    private int page;
    //页面数据
    private List<WebPage> webPages;

    public int getPageSize(){
        return webPages.size();
    }

    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }

    public List<WebPage> getWebPages() {
        return webPages;
    }

    public void setWebPages(List<WebPage> webPages) {
        this.webPages = webPages;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("total", total)
                .append("page", page)
                .append("webPages", webPages)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof SearchResult))
            return false;

        SearchResult that = (SearchResult) o;

        return new EqualsBuilder()
                .append(getTotal(), that.getTotal())
                .append(getPage(), that.getPage())
                .append(getWebPages(), that.getWebPages())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getTotal())
                .append(getPage())
                .append(getWebPages())
                .toHashCode();
    }
}
