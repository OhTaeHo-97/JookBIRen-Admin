package com.ablez.admin.first_db.quiz.service;

import com.ablez.admin.first_db.quiz.dto.QuizDto.QuizInfoDto;
import com.ablez.admin.first_db.quiz.dto.QuizDto.WrongAnswerDto;
import com.ablez.admin.first_db.quiz.entity.episode2.Quiz0Ep02;
import com.ablez.admin.first_db.quiz.entity.episode2.Quiz1Ep02;
import com.ablez.admin.first_db.quiz.entity.episode2.Quiz2Ep02;
import com.ablez.admin.first_db.quiz.entity.episode2.Quiz3Ep02;
import com.ablez.admin.first_db.quiz.entity.episode2.WrongAnswerEp02;
import com.ablez.admin.first_db.quiz.repository.episode2.Ep2Quiz0Repository;
import com.ablez.admin.first_db.quiz.repository.episode2.Ep2Quiz1Repository;
import com.ablez.admin.first_db.quiz.repository.episode2.Ep2Quiz2Repository;
import com.ablez.admin.first_db.quiz.repository.episode2.Ep2Quiz3Repository;
import com.ablez.admin.first_db.quiz.repository.episode2.Ep2WrongAnswerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class Ep02QuizInfoService {
    private final Ep2Quiz0Repository ep2Quiz0Repository;
    private final Ep2Quiz1Repository ep2Quiz1Repository;
    private final Ep2Quiz2Repository ep2Quiz2Repository;
    private final Ep2Quiz3Repository ep2Quiz3Repository;
    private final Ep2WrongAnswerRepository ep2WrongAnswerRepository;

    public List<QuizInfoDto> findAllQuizInfos() {
        List<QuizInfoDto> quizzes = new ArrayList<>();
        quizzes.addAll(findQuiz0Infos());
        quizzes.addAll(findQuiz1Infos());
        quizzes.addAll(findQuiz2Infos());
        quizzes.addAll(findQuiz3Infos());
        return quizzes;
    }

    private List<QuizInfoDto> findQuiz0Infos() {
        List<Quiz0Ep02> quizzes = ep2Quiz0Repository.findAll();
        return quizzes.stream().map(quiz -> QuizInfoDto.builder()
                        .code(quiz.getUserId().getCode())
                        .placeNumber(0)
                        .firstAccessTime(quiz.getFirstAccessTime())
                        .firstAnswerTime(quiz.getFirstAnswerTime())
                        .getFirstHintTime(quiz.getFirstGetHintTime())
                        .getSecondHintTime(quiz.getSecondGetHintTime())
                        .getAnswerTime(quiz.getGetAnswerTime())
                        .build())
                .collect(Collectors.toList());
    }

    private List<QuizInfoDto> findQuiz1Infos() {
        List<Quiz1Ep02> quizzes = ep2Quiz1Repository.findAll();
        return quizzes.stream().map(quiz -> QuizInfoDto.builder()
                        .code(quiz.getUserId().getCode())
                        .placeNumber(0)
                        .firstAccessTime(quiz.getFirstAccessTime())
                        .firstAnswerTime(quiz.getFirstAnswerTime())
                        .getFirstHintTime(quiz.getFirstGetHintTime())
                        .getSecondHintTime(quiz.getSecondGetHintTime())
                        .getAnswerTime(quiz.getGetAnswerTime())
                        .build())
                .collect(Collectors.toList());
    }

    private List<QuizInfoDto> findQuiz2Infos() {
        List<Quiz2Ep02> quizzes = ep2Quiz2Repository.findAll();
        return quizzes.stream().map(quiz -> QuizInfoDto.builder()
                        .code(quiz.getUserId().getCode())
                        .placeNumber(0)
                        .firstAccessTime(quiz.getFirstAccessTime())
                        .firstAnswerTime(quiz.getFirstAnswerTime())
                        .getFirstHintTime(quiz.getFirstHintTime())
                        .getSecondHintTime(quiz.getSecondHintTime())
                        .getAnswerTime(quiz.getGetAnswerTime())
                        .build())
                .collect(Collectors.toList());
    }

    private List<QuizInfoDto> findQuiz3Infos() {
        List<Quiz3Ep02> quizzes = ep2Quiz3Repository.findAll();
        return quizzes.stream().map(quiz -> QuizInfoDto.builder()
                        .code(quiz.getUserId().getCode())
                        .placeNumber(0)
                        .firstAccessTime(quiz.getFirstAccessTime())
                        .firstAnswerTime(quiz.getFirstAnswerTime())
                        .getFirstHintTime(quiz.getGetHintTime())
                        .getSecondHintTime(null)
                        .getAnswerTime(quiz.getGetAnswerTime())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<WrongAnswerDto> findAllWrongAnswerInfos() {
        List<WrongAnswerEp02> wrongAnswers = ep2WrongAnswerRepository.findAll();
        return wrongAnswers.stream().map(wrongAnswer -> WrongAnswerDto.builder()
                        .code(wrongAnswer.getUser().getCode())
                        .placeNumber(wrongAnswer.getQuiz().getPlaceCode())
                        .quizNumber(wrongAnswer.getQuiz().getQuizNumber())
                        .answer(wrongAnswer.getAnswer())
                        .time(wrongAnswer.getTime())
                        .build())
                .collect(Collectors.toList());
    }
}
