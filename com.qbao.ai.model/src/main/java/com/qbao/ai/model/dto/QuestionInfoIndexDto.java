package com.qbao.ai.model.dto;

import java.util.List;



/**
 * Created by shuaizhihu on 2016/12/2.
 */
public class QuestionInfoIndexDto {
    private long id;
    private String question;
    private String answer;
    private List<com.qbao.ai.model.dto.QuestionLinkIndexDto> links;
    private List<QuestionSimIndexDto>  simQustions;

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

    public List<QuestionLinkIndexDto> getLinks() {
        return links;
    }

    public void setLinks(List<QuestionLinkIndexDto> links) {
        this.links = links;
    }

    public List<QuestionSimIndexDto> getSimQustions() {
        return simQustions;
    }

    public void setSimQustions(List<QuestionSimIndexDto> simQustions) {
        this.simQustions = simQustions;
    }

    @Override
    public String toString() {
        return "QuestionInfoIndexDto{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", links=" + links +
                ", simQustions=" + simQustions +
                '}';
    }
}
