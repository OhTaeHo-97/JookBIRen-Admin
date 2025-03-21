package com.ablez.admin.quiz.repository.episode1;

import static com.ablez.admin.first_db.quiz.entity.episode1.QQuizEp01.quizEp01;

import com.ablez.admin.quiz.entity.episode1.QuizEp01;
import com.ablez.admin.repository.FirstQuerydsl4RepositorySupport;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class Ep1QuizRepository extends FirstQuerydsl4RepositorySupport {
    public Ep1QuizRepository() {
        super(QuizEp01.class);
    }

    public Optional<QuizEp01> findByPlaceNumberAndQuizNumber(int placeCode, int quizNumber) {
        return Optional.ofNullable(
                selectFrom(quizEp01)
                        .where(
                                quizEp01.placeCode.eq(placeCode),
                                quizEp01.quizNumber.eq(quizNumber)
                        )
                        .fetchOne()
        );
    }
}
