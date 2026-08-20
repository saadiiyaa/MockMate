package com.mockmate.model;

import jakarta.persistence.*;

@Entity
public class InterviewSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    private String difficulty;

    private int currentQuestionIndex;

    private boolean completed;

    public InterviewSession() {
    }

    public InterviewSession(String role, String difficulty) {
        this.role = role;
        this.difficulty = difficulty;
        this.currentQuestionIndex = 0;
        this.completed = false;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setCurrentQuestionIndex(int currentQuestionIndex) {
        this.currentQuestionIndex = currentQuestionIndex;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}