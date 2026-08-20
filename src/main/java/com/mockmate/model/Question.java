package com.mockmate.model;
import jakarta.persistence.*;
@Entity
public class Question {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private String role, category, difficulty;
 @Column(length=1000) private String text;
 public Question() {}
 public Question(String role,String category,String difficulty,String text){this.role=role;this.category=category;this.difficulty=difficulty;this.text=text;}
 public Long getId(){return id;} public String getRole(){return role;} public String getCategory(){return category;} public String getDifficulty(){return difficulty;} public String getText(){return text;}
 public void setId(Long v){id=v;} public void setRole(String v){role=v;} public void setCategory(String v){category=v;} public void setDifficulty(String v){difficulty=v;} public void setText(String v){text=v;}
}
