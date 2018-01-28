
package com.qbao.ai.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class QuestionDto {

    @SerializedName("dirId")
    @Expose
    private Long dirId;
    @SerializedName("dirName")
    @Expose
    private String dirName;
    @SerializedName("questions")
    @Expose
    private List<Question> questions = null;

    public Long getDirId() {
        return dirId;
    }

    public void setDirId(Long dirId) {
        this.dirId = dirId;
    }

    public QuestionDto withDirId(Long dirId) {
        this.dirId = dirId;
        return this;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public QuestionDto withDirName(String dirName) {
        this.dirName = dirName;
        return this;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public QuestionDto withQuestions(List<Question> questions) {
        this.questions = questions;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(dirId).append(dirName).append(questions).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof QuestionDto) == false) {
            return false;
        }
        QuestionDto rhs = ((QuestionDto) other);
        return new EqualsBuilder().append(dirId, rhs.dirId).append(dirName, rhs.dirName).append(questions, rhs.questions).isEquals();
    }

}
