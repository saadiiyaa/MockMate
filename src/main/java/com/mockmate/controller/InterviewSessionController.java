package com.mockmate.controller;

import com.mockmate.model.Answer;
import com.mockmate.model.InterviewSession;
import com.mockmate.model.Question;
import com.mockmate.repository.AnswerRepository;
import com.mockmate.repository.InterviewSessionRepository;
import com.mockmate.repository.QuestionRepository;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/interviews")
@CrossOrigin
public class InterviewSessionController {

    private final InterviewSessionRepository interviewSessionRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public InterviewSessionController(
            InterviewSessionRepository interviewSessionRepository,
            AnswerRepository answerRepository,
            QuestionRepository questionRepository) {

        this.interviewSessionRepository = interviewSessionRepository;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }


    // =========================
    // CREATE INTERVIEW SESSION
    // =========================

    @PostMapping
    public InterviewSession createInterviewSession(
            @RequestBody InterviewSession session) {

        session.setCurrentQuestionIndex(0);
        session.setCompleted(false);

        return interviewSessionRepository.save(session);
    }


    // =========================
    // GET INTERVIEW RESULT
    // =========================

    @GetMapping("/{id}/result")
    public Map<String, Object> getInterviewResult(
            @PathVariable Long id) {

        InterviewSession session =
                interviewSessionRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Interview session not found"));

        List<Answer> answers =
                answerRepository.findByInterviewSessionId(id);

        int totalScore = 0;

        List<Map<String, Object>> results =
                new ArrayList<>();

        for (Answer answer : answers) {

            totalScore += answer.getScore();

            Question question =
                    questionRepository.findById(
                            answer.getQuestionId()
                    ).orElse(null);

            results.add(
                    Map.of(
                            "questionId",
                            answer.getQuestionId(),

                            "question",
                            question != null
                                    ? question.getText()
                                    : "Question not found",

                            "answer",
                            answer.getAnswerText(),

                            "score",
                            answer.getScore(),

                            "matchedKeywords",
                            answer.getMatchedKeywords() != null
                                    ? answer.getMatchedKeywords()
                                    : "",

                            "missingKeywords",
                            answer.getMissingKeywords() != null
                                    ? answer.getMissingKeywords()
                                    : "",

                            "feedback",
                            answer.getFeedback() != null
                                    ? answer.getFeedback()
                                    : ""
                    )
            );
        }

        double averageScore =
                answers.isEmpty()
                        ? 0
                        : (double) totalScore / answers.size();

        return Map.of(
                "interviewSessionId",
                session.getId(),

                "role",
                session.getRole(),

                "difficulty",
                session.getDifficulty(),

                "totalQuestionsAnswered",
                answers.size(),

                "averageScore",
                averageScore,

                "completed",
                session.isCompleted(),

                "results",
                results
        );
    }


    // =========================
    // INTERVIEW HISTORY
    // =========================

    @GetMapping("/history")
    public List<Map<String, Object>> getInterviewHistory() {

        List<InterviewSession> sessions =
                interviewSessionRepository.findAll();

        List<Map<String, Object>> history =
                new ArrayList<>();

        for (InterviewSession session : sessions) {

            List<Answer> answers =
                    answerRepository.findByInterviewSessionId(
                            session.getId()
                    );

            int totalScore = 0;

            for (Answer answer : answers) {

                totalScore += answer.getScore();

            }

            double averageScore =
                    answers.isEmpty()
                            ? 0
                            : (double) totalScore / answers.size();

            history.add(
                    Map.of(
                            "interviewSessionId",
                            session.getId(),

                            "role",
                            session.getRole(),

                            "difficulty",
                            session.getDifficulty(),

                            "score",
                            Math.round(averageScore),

                            "questionsAnswered",
                            answers.size(),

                            "completed",
                            session.isCompleted()
                    )
            );
        }

        return history;
    }

}