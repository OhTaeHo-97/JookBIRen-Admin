package com.ablez.admin.quiz.repository.episode2;

import static com.ablez.admin.quiz.entity.episode2.QQuizEp02.quizEp02;

import com.ablez.admin.quiz.entity.episode2.QuizEp02;
import com.ablez.admin.repository.FirstQuerydsl4RepositorySupport;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class Ep2QuizRepository extends FirstQuerydsl4RepositorySupport {
    public Ep2QuizRepository() {
        super(QuizEp02.class);
    }

    public Optional<QuizEp02> findByPlaceNumberAndQuizNumber(int placeCode, int quizNumber) {
        return Optional.ofNullable(
                selectFrom(quizEp02)
                        .where(
                                quizEp02.placeCode.eq(placeCode),
                                quizEp02.quizNumber.eq(quizNumber)
                        )
                        .fetchOne()
        );
    }
}
