package com.mockmate.model;

import jakarta.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long questionId;

    private Long interviewSessionId;

    private int score;

    @Column(length = 5000)
    private String answerText;

    @Column(length = 2000)
    private String matchedKeywords;

    @Column(length = 2000)
    private String missingKeywords;

    @Column(length = 5000)
    private String feedback;


    public Answer() {}


    public Answer(
            Long questionId,
            Long interviewSessionId,
            String answerText) {

        this.questionId = questionId;
        this.interviewSessionId = interviewSessionId;
        this.answerText = answerText;
    }


    public Long getId() {
        return id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public Long getInterviewSessionId() {
        return interviewSessionId;
    }

    public int getScore() {
        return score;
    }

    public String getAnswerText() {
        return answerText;
    }

    public String getMatchedKeywords() {
        return matchedKeywords;
    }

    public String getMissingKeywords() {
        return missingKeywords;
    }

    public String getFeedback() {
        return feedback;
    }


    public void setId(Long v) {
        id = v;
    }

    public void setQuestionId(Long v) {
        questionId = v;
    }

    public void setInterviewSessionId(Long v) {
        interviewSessionId = v;
    }

    public void setScore(int v) {
        score = v;
    }

    public void setAnswerText(String v) {
        answerText = v;
    }

    public void setMatchedKeywords(String v) {
        matchedKeywords = v;
    }

    public void setMissingKeywords(String v) {
        missingKeywords = v;
    }

    public void setFeedback(String v) {
        feedback = v;
    }
}