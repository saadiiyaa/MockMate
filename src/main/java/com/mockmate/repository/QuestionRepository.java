package com.mockmate.repository;
import com.mockmate.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface QuestionRepository extends JpaRepository<Question,Long> {
 List<Question> findByRoleIgnoreCaseAndDifficultyIgnoreCase(String role,String difficulty);
}
