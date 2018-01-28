package com.qbao.ai.rest.dto;

import java.util.List;

/**
 * Created by shuaizhihu on 2016/12/2.
 */
public class GuessQuestionDTO {
    private long dirId;
    private String dirName;
    private List<QuestionSimDTO> questions;

    public long getDirId() {
        return dirId;
    }

    public void setDirId(long dirId) {
        this.dirId = dirId;
    }

    public List<QuestionSimDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionSimDTO> questions) {
        this.questions = questions;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }
}
