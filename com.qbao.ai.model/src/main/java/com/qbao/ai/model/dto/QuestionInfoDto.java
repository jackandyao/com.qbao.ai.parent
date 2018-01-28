package com.qbao.ai.model.dto;

import java.util.Date;

/**
 * Created by shuaizhihu on 2016/11/30.
 */
public class QuestionInfoDto {
    private long id;
    private String question;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "QuestionInfoDto{" +
                "id=" + id +
                ", question='" + question + '\'' +
                '}';
    }
}
