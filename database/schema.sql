CREATE DATABASE IF NOT EXISTS mockmate;
USE mockmate;
INSERT INTO question (role, category, difficulty, text) VALUES
('Java Developer','OOP','Beginner','What are the four main principles of Object-Oriented Programming?'),
('Java Developer','Collections','Intermediate','What is the difference between ArrayList and LinkedList in Java?'),
('Java Developer','Exceptions','Beginner','What is the difference between checked and unchecked exceptions?'),
('Java Developer','JDBC','Intermediate','Explain the basic steps used to connect a Java application to MySQL using JDBC.'),
('Java Developer','SQL','Intermediate','What is the difference between INNER JOIN and LEFT JOIN?'),
('Software Engineer','Problem Solving','Beginner','How would you approach debugging a program that produces an unexpected result?');
