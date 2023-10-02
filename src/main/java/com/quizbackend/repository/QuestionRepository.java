package com.quizbackend.repository;

import com.quizbackend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM Question q WHERE q.category= :category order BY random() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category, int numQ);
}
