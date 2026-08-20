package com.mockmate.controller;

import org.springframework.web.bind.annotation.*;

import com.mockmate.AnswerEvaluator;

import java.util.Map;

@RestController
@RequestMapping("/api/evaluate")
@CrossOrigin(origins = "*")
public class EvaluationController {

    @PostMapping
    public Map<String, Object> evaluate(
            @RequestBody EvaluationRequest request) {

        AnswerEvaluator.EvaluationResult result =
                AnswerEvaluator.evaluate(
                        request.question(),
                        request.answer()
                );

        return Map.of(
                "score", result.score(),
                "question", request.question(),
                "matchedKeywords", result.matchedKeywords(),
                "missingKeywords", result.missingKeywords(),
                "feedback", result.feedback()
        );
    }

    public record EvaluationRequest(
            String question,
            String answer
    ) {
    }
}