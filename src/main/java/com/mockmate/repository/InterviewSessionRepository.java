package com.mockmate.repository;

import com.mockmate.model.InterviewSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewSessionRepository
        extends JpaRepository<InterviewSession, Long> {
}
