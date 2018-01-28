
package com.qbao.ai.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionSearchDto {
    //
    @SerializedName("link")
    @Expose
    private String link;
    //图标
    @SerializedName("icon")
    @Expose
    private String icon;
    //来源
    @SerializedName("source")
    @Expose
    private String source;
    //类型
    @SerializedName("type")
    @Expose
    private  String type;
    //标题
    @SerializedName("summary")
    @Expose
    private String summary;
    //内容
    @SerializedName("content")
    @Expose
    private String content;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return "QuestionSearchDto{" +
                "link='" + link + '\'' +
                ", icon='" + icon + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
