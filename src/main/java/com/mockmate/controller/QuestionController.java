package com.mockmate.controller;
import com.mockmate.model.Question;
import com.mockmate.repository.QuestionRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/questions") @CrossOrigin
public class QuestionController {
 private final QuestionRepository repo;
 public QuestionController(QuestionRepository repo){this.repo=repo;}
 @GetMapping public List<Question> get(@RequestParam(defaultValue="Java Developer") String role,@RequestParam(defaultValue="Beginner") String difficulty){
  return repo.findByRoleIgnoreCaseAndDifficultyIgnoreCase(role,difficulty);
 }
}
