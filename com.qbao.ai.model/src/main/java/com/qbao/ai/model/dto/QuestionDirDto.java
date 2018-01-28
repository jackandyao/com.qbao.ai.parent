package com.qbao.ai.model.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by shuaizhihu on 2016/11/30.
 */
public class QuestionDirDto {

    private long dirId;
    private String dirName;
    private List<QuestionInfoDto> questions;

    public long getDirId() {
        return dirId;
    }

    public void setDirId(long dirId) {
        this.dirId = dirId;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public List<QuestionInfoDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionInfoDto> questions) {
        this.questions = questions;
    }
}
