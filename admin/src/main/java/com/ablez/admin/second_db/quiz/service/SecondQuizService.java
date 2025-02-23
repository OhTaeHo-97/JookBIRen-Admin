package com.ablez.admin.second_db.quiz.service;

import com.ablez.admin.second_db.quiz.entity.QuizEp01;
import com.ablez.admin.second_db.quiz.entity.QuizEp02;
import com.ablez.admin.second_db.quiz.repository.QuizEp01QuerydslRepository;
import com.ablez.admin.second_db.quiz.repository.QuizEp02QuerydslRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class SecondQuizService {
    private final QuizEp01QuerydslRepository quizEp1Repository;
    private final QuizEp02QuerydslRepository quizEp2Repository;

    public List<QuizEp01> findAllEp01() {
        return quizEp1Repository.findAll();
    }

    public List<QuizEp02> findAllEp02() {
        return quizEp2Repository.findAll();
    }
}
