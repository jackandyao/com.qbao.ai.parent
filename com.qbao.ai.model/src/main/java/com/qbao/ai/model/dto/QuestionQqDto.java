package com.qbao.ai.model.dto;

import java.util.Date;

/**
 * Created by shuaizhihu on 2016/12/1.
 */
public class QuestionQqDto {
    private long id;
    private String qq;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Override
    public String toString() {
        return "QuestionQqDto{" +
                "id=" + id +
                ", qq='" + qq + '\'' +
                '}';
    }
}
