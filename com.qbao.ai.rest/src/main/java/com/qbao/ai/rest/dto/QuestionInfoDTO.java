package com.qbao.ai.rest.dto;

import java.util.List;

/**
 * Created by shuaizhihu on 2016/12/2.
 */
public class QuestionInfoDTO {
    private long id;
    private String question;
    private String answer;
    private List<QuestionLinkDTO> links;
    private List<QuestionSimDTO>  simQustions;

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<QuestionLinkDTO> getLinks() {
        return links;
    }

    public void setLinks(List<QuestionLinkDTO> links) {
        this.links = links;
    }

    public List<QuestionSimDTO> getSimQustions() {
        return simQustions;
    }

    public void setSimQustions(List<QuestionSimDTO> simQustions) {
        this.simQustions = simQustions;
    }
}
