package com.qbao.ai.model.html;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author wangping
 * @createTime 2017/4/17 上午11:26
 * $$LastChangedDate: 2017-04-17 14:51:49 +0800 (Mon, 17 Apr 2017) $$
 * $$LastChangedRevision: 128 $$
 * $$LastChangedBy: wangping $$
 */
public class WebPage {
    // 标题
    private String title;
    // 链接
    private String url;
    // 简介
    private String summary;
    // 正文内容
    private String content;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("url", url)
                .append("summary", summary)
                .append("content", content)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof WebPage))
            return false;

        WebPage webPage = (WebPage) o;

        return new EqualsBuilder()
                .append(getTitle(), webPage.getTitle())
                .append(getUrl(), webPage.getUrl())
                .append(getSummary(), webPage.getSummary())
                .append(getContent(), webPage.getContent())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getTitle())
                .append(getUrl())
                .append(getSummary())
                .append(getContent())
                .toHashCode();
    }
}
