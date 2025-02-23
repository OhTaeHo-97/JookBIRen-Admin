package com.ablez.admin.second_db.hint.repository;

import static com.ablez.admin.second_db.hint.entity.QHintEp02.hintEp02;
import static com.ablez.admin.second_db.quiz.entity.QQuizEp02.quizEp02;

import com.ablez.admin.second_db.hint.entity.HintEp02;
import com.ablez.admin.second_db.repository.SecondQuerydsl4RepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class HintEp02QuerydslRepository extends SecondQuerydsl4RepositorySupport {
    public HintEp02QuerydslRepository() {
        super(HintEp02.class);
    }

    public List<HintEp02> findAll() {
        return selectFrom(hintEp02)
                .innerJoin(hintEp02.quiz, quizEp02).fetchJoin()
                .fetch();
    }
}
