package com.mockmate.controller;

import com.mockmate.model.InterviewSession;
import com.mockmate.model.Question;
import com.mockmate.repository.InterviewSessionRepository;
import com.mockmate.repository.QuestionRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
@CrossOrigin(origins = "*")
public class InterviewController {

    private final InterviewSessionRepository interviewSessionRepository;
    private final QuestionRepository questionRepository;

    public InterviewController(
            InterviewSessionRepository interviewSessionRepository,
            QuestionRepository questionRepository) {

        this.interviewSessionRepository = interviewSessionRepository;
        this.questionRepository = questionRepository;
    }

    @PostMapping("/start")
    public InterviewSession startInterview(
            @RequestParam(defaultValue = "Java Developer") String role,
            @RequestParam(defaultValue = "Beginner") String difficulty) {

        InterviewSession session =
                new InterviewSession(role, difficulty);

        return interviewSessionRepository.save(session);
    }

    @GetMapping("/{id}/question")
    public Question getCurrentQuestion(@PathVariable Long id) {

        InterviewSession session =
                interviewSessionRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Interview session not found"));

        List<Question> questions =
                questionRepository.findByRoleIgnoreCaseAndDifficultyIgnoreCase(
                        session.getRole(),
                        session.getDifficulty()
                );

        if (questions.isEmpty()) {
            throw new RuntimeException("No questions found for this interview");
        }

        int index = session.getCurrentQuestionIndex();

        if (index >= questions.size()) {
            throw new RuntimeException("Interview completed");
        }

        return questions.get(index);
    }
}