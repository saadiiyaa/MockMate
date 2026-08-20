package com.mockmate;

import java.util.*;

public class AnswerEvaluator {

    private static final Map<String, List<String>> KEYWORDS = new HashMap<>();

    static {
        KEYWORDS.put("What are the four main principles of Object-Oriented Programming?",
                Arrays.asList("encapsulation", "inheritance", "polymorphism", "abstraction"));

        KEYWORDS.put("What is the difference between ArrayList and LinkedList in Java?",
                Arrays.asList("arraylist", "linkedlist", "random access", "insertion", "deletion"));

        KEYWORDS.put("What is the difference between checked and unchecked exceptions?",
                Arrays.asList("checked", "unchecked", "compile", "runtime", "exception"));

        KEYWORDS.put("Explain the basic steps used to connect a Java application to MySQL using JDBC.",
                Arrays.asList("jdbc", "driver", "connection", "mysql", "statement", "resultset"));

        KEYWORDS.put("What is the difference between INNER JOIN and LEFT JOIN?",
                Arrays.asList("inner join", "left join", "matching", "rows", "table"));

        KEYWORDS.put("How would you approach debugging a program that produces an unexpected result?",
                Arrays.asList("debugger", "breakpoint", "error", "logs", "test", "debug"));

        KEYWORDS.put("What is the difference between JDK, JRE and JVM?",
                Arrays.asList("jdk", "jre", "jvm", "compiler", "runtime", "bytecode"));

        KEYWORDS.put("What is encapsulation in Java?",
                Arrays.asList("data", "hiding", "private", "getter", "setter"));

        KEYWORDS.put("What is inheritance in Java?",
                Arrays.asList("extends", "parent", "child", "class", "reuse"));

        KEYWORDS.put("What is the difference between method overloading and method overriding?",
                Arrays.asList("overloading", "overriding", "compile", "runtime", "method"));

        KEYWORDS.put("What is the purpose of try, catch and finally in Java?",
                Arrays.asList("exception", "try", "catch", "finally", "error"));

        KEYWORDS.put("What is the difference between List, Set and Map?",
                Arrays.asList("list", "set", "map", "duplicate", "key", "value"));

        KEYWORDS.put("Why is String immutable in Java?",
                Arrays.asList("immutable", "security", "string pool", "thread", "cache"));

        KEYWORDS.put("What is the difference between == and equals() in Java?",
                Arrays.asList("reference", "value", "equals", "object"));

        KEYWORDS.put("Explain abstraction and how it is implemented in Java.",
                Arrays.asList("abstraction", "abstract", "class", "interface", "implementation"));

        KEYWORDS.put("What is the difference between HashMap and HashSet?",
                Arrays.asList("hashmap", "hashset", "key", "value", "duplicate"));

        KEYWORDS.put("How does HashMap work internally in Java?",
                Arrays.asList("hash", "bucket", "key", "value", "hashcode", "equals"));

        KEYWORDS.put("What is the difference between throw and throws?",
                Arrays.asList("throw", "throws", "exception", "method", "explicit"));

        KEYWORDS.put("What is the difference between Thread and Runnable in Java?",
                Arrays.asList("thread", "runnable", "run", "start", "interface"));

        KEYWORDS.put("What is synchronization and why is it used in Java?",
                Arrays.asList("synchronization", "thread", "lock", "shared", "concurrency"));

        KEYWORDS.put("What are lambda expressions in Java?",
                Arrays.asList("lambda", "functional", "interface", "expression", "arrow"));

        KEYWORDS.put("What is the Stream API in Java?",
                Arrays.asList("stream", "filter", "map", "collect", "pipeline"));

        KEYWORDS.put("What is the difference between Statement and PreparedStatement?",
                Arrays.asList("statement", "preparedstatement", "sql", "parameter", "injection"));

        KEYWORDS.put("What is normalization in a relational database?",
                Arrays.asList("normalization", "redundancy", "1nf", "2nf", "3nf", "database"));

        KEYWORDS.put("What is the difference between synchronized, volatile and atomic variables?",
                Arrays.asList("synchronized", "volatile", "atomic", "thread", "visibility", "concurrency"));

        KEYWORDS.put("What are the differences between HashMap, ConcurrentHashMap and Hashtable?",
                Arrays.asList("hashmap", "concurrenthashmap", "hashtable", "thread", "synchronized", "concurrency"));

        KEYWORDS.put("Explain intermediate and terminal operations in Java Streams.",
                Arrays.asList("intermediate", "terminal", "filter", "map", "collect", "stream"));

        KEYWORDS.put("Explain heap memory, stack memory and garbage collection in Java.",
                Arrays.asList("heap", "stack", "garbage collection", "memory", "object"));

        KEYWORDS.put("What is deadlock in Java and how can it be prevented?",
                Arrays.asList("deadlock", "thread", "lock", "synchronization", "resource"));

        KEYWORDS.put("Explain the Singleton design pattern and its advantages and disadvantages.",
                Arrays.asList("singleton", "instance", "constructor", "private", "global"));

        KEYWORDS.put("What is database connection pooling and why is it useful?",
                Arrays.asList("connection pool", "database", "connection", "performance", "reuse"));

        KEYWORDS.put("What is the difference between compile-time and runtime errors?",
                Arrays.asList("compile-time", "runtime", "compiler", "execution", "error"));

        KEYWORDS.put("What is the difference between an array and a linked list?",
                Arrays.asList("array", "linked list", "index", "memory", "insertion", "deletion"));

        KEYWORDS.put("What is time complexity and why is it important?",
                Arrays.asList("time complexity", "algorithm", "performance", "input", "big o"));
    }

    public static int calculateScore(String question, String answer) {

        if (answer == null || answer.trim().isEmpty()) {
            return 0;
        }

        List<String> keywords = KEYWORDS.get(question);

        if (keywords == null) {
            return 0;
        }

        String normalizedAnswer = answer.toLowerCase();

        int matched = 0;

        for (String keyword : keywords) {
            if (normalizedAnswer.contains(keyword.toLowerCase())) {
                matched++;
            }
        }

        return (matched * 100) / keywords.size();
    }

    public static EvaluationResult evaluate(String question, String answer) {

        if (answer == null || answer.trim().isEmpty()) {
            return new EvaluationResult(
                    0,
                    List.of(),
                    KEYWORDS.getOrDefault(question, List.of()),
                    "No answer was provided."
            );
        }

        List<String> keywords = KEYWORDS.get(question);

        if (keywords == null) {
            return new EvaluationResult(
                    0,
                    List.of(),
                    List.of(),
                    "This question does not have evaluation keywords configured yet."
            );
        }

        String normalizedAnswer = answer.toLowerCase();

        List<String> matchedKeywords = new ArrayList<>();
        List<String> missingKeywords = new ArrayList<>();

        for (String keyword : keywords) {

            if (normalizedAnswer.contains(keyword.toLowerCase())) {
                matchedKeywords.add(keyword);
            } else {
                missingKeywords.add(keyword);
            }
        }

        int score = (matchedKeywords.size() * 100) / keywords.size();

        String feedback;

        if (score == 100) {
            feedback = "Excellent answer. You covered all the expected concepts.";
        } else if (score >= 75) {
            feedback = "Good answer. You covered most of the expected concepts, but there is still room for improvement.";
        } else if (score >= 50) {
            feedback = "Decent answer, but you should explain more of the key concepts.";
        } else if (score > 0) {
            feedback = "Your answer covers some relevant concepts, but several important points are missing.";
        } else {
            feedback = "Your answer needs improvement. Try to cover the main concepts expected for this question.";
        }

        return new EvaluationResult(
                score,
                matchedKeywords,
                missingKeywords,
                feedback
        );
    }

    public record EvaluationResult(
            int score,
            List<String> matchedKeywords,
            List<String> missingKeywords,
            String feedback
    ) {
    }
}