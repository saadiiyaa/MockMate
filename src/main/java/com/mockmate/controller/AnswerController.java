package com.mockmate.controller;

import com.mockmate.AnswerEvaluator;
import com.mockmate.model.Answer;
import com.mockmate.model.InterviewSession;
import com.mockmate.model.Question;
import com.mockmate.repository.AnswerRepository;
import com.mockmate.repository.InterviewSessionRepository;
import com.mockmate.repository.QuestionRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/answers")
@CrossOrigin
public class AnswerController {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final InterviewSessionRepository interviewSessionRepository;

    public AnswerController(
            AnswerRepository answerRepository,
            QuestionRepository questionRepository,
            InterviewSessionRepository interviewSessionRepository) {

        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.interviewSessionRepository = interviewSessionRepository;
    }


    @PostMapping
    public Map<String, Object> submitAnswer(
            @RequestBody Answer answer) {


        // =========================
        // FIND INTERVIEW SESSION
        // =========================

        InterviewSession session =
                interviewSessionRepository.findById(
                        answer.getInterviewSessionId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Interview session not found"
                        )
                );


        // =========================
        // FIND QUESTION
        // =========================

        Question question =
                questionRepository.findById(
                        answer.getQuestionId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Question not found"
                        )
                );


        // =========================
        // EVALUATE ANSWER
        // =========================

        AnswerEvaluator.EvaluationResult result =
                AnswerEvaluator.evaluate(
                        question.getText(),
                        answer.getAnswerText()
                );


        // =========================
        // SAVE EVALUATION
        // =========================

        answer.setScore(
                result.score()
        );

        answer.setMatchedKeywords(
                String.join(
                        ", ",
                        result.matchedKeywords()
                )
        );

        answer.setMissingKeywords(
                String.join(
                        ", ",
                        result.missingKeywords()
                )
        );

        answer.setFeedback(
                result.feedback()
        );


        // =========================
        // SAVE ANSWER
        // =========================

        Answer savedAnswer =
                answerRepository.save(answer);


        // =========================
        // UPDATE INTERVIEW
        // =========================

        List<Question> questions =
                questionRepository
                        .findByRoleIgnoreCaseAndDifficultyIgnoreCase(
                                session.getRole(),
                                session.getDifficulty()
                        );


        int nextIndex =
                session.getCurrentQuestionIndex() + 1;


        session.setCurrentQuestionIndex(
                nextIndex
        );


        if (nextIndex >= questions.size()) {

            session.setCompleted(true);

        }


        interviewSessionRepository.save(
                session
        );


        // =========================
        // RETURN RESULT
        // =========================

        return Map.of(

                "answerId",
                savedAnswer.getId(),

                "questionId",
                question.getId(),

                "question",
                question.getText(),

                "answer",
                savedAnswer.getAnswerText(),

                "score",
                result.score(),

                "matchedKeywords",
                result.matchedKeywords(),

                "missingKeywords",
                result.missingKeywords(),

                "feedback",
                result.feedback(),

                "nextQuestionIndex",
                session.getCurrentQuestionIndex(),

                "completed",
                session.isCompleted()
        );
    }
}