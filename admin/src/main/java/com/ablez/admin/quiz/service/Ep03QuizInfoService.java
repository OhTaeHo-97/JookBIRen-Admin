package com.ablez.admin.quiz.service;

import com.ablez.admin.quiz.dto.QuizDto.QuizInfoDto;
import com.ablez.admin.quiz.dto.QuizDto.WrongAnswerDto;
import com.ablez.admin.quiz.entity.episode3.Quiz0Ep03;
import com.ablez.admin.quiz.entity.episode3.Quiz1Ep03;
import com.ablez.admin.quiz.entity.episode3.Quiz2Ep03;
import com.ablez.admin.quiz.entity.episode3.Quiz3Ep03;
import com.ablez.admin.quiz.entity.episode3.WrongAnswerEp03;
import com.ablez.admin.quiz.repository.episode3.Ep3Quiz0Repository;
import com.ablez.admin.quiz.repository.episode3.Ep3Quiz1Repository;
import com.ablez.admin.quiz.repository.episode3.Ep3Quiz2Repository;
import com.ablez.admin.quiz.repository.episode3.Ep3Quiz3Repository;
import com.ablez.admin.quiz.repository.episode3.Ep3WrongAnswerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class Ep03QuizInfoService {
    private final Ep3Quiz0Repository ep3Quiz0Repository;
    private final Ep3Quiz1Repository ep3Quiz1Repository;
    private final Ep3Quiz2Repository ep3Quiz2Repository;
    private final Ep3Quiz3Repository ep3Quiz3Repository;
    private final Ep3WrongAnswerRepository ep3WrongAnswerRepository;

    public List<QuizInfoDto> findAllQuizInfos() {
        List<QuizInfoDto> quizzes = new ArrayList<>();
        quizzes.addAll(findQuiz0Infos());
        quizzes.addAll(findQuiz1Infos());
        quizzes.addAll(findQuiz2Infos());
        quizzes.addAll(findQuiz3Infos());
        return quizzes;
    }

    private List<QuizInfoDto> findQuiz0Infos() {
        List<Quiz0Ep03> quizzes = ep3Quiz0Repository.findAll();
        return quizzes.stream().map(quiz -> QuizInfoDto.builder()
                        .code(quiz.getUserId().getCode())
                        .placeNumber(0)
                        .quizNumber(quiz.getQuizNumber())
                        .firstAccessTime(quiz.getFirstAccessTime())
                        .firstAnswerTime(quiz.getFirstAnswerTime())
                        .getFirstHintTime(quiz.getFirstHintTime())
                        .getSecondHintTime(quiz.getSecondHintTime())
                        .getAnswerTime(quiz.getGetAnswerTime())
                        .build())
                .collect(Collectors.toList());
    }

    private List<QuizInfoDto> findQuiz1Infos() {
        List<Quiz1Ep03> quizzes = ep3Quiz1Repository.findAll();
        return quizzes.stream().map(quiz -> QuizInfoDto.builder()
                        .code(quiz.getUserId().getCode())
                        .placeNumber(1)
                        .quizNumber(quiz.getQuizNumber())
                        .firstAccessTime(quiz.getFirstAccessTime())
                        .firstAnswerTime(quiz.getFirstAnswerTime())
                        .getFirstHintTime(quiz.getFirstHintTime())
                        .getSecondHintTime(quiz.getSecondHintTime())
                        .getAnswerTime(quiz.getGetAnswerTime())
                        .build())
                .collect(Collectors.toList());
    }

    private List<QuizInfoDto> findQuiz2Infos() {
        List<Quiz2Ep03> quizzes = ep3Quiz2Repository.findAll();
        return quizzes.stream().map(quiz -> QuizInfoDto.builder()
                        .code(quiz.getUserId().getCode())
                        .placeNumber(2)
                        .quizNumber(quiz.getQuizNumber())
                        .firstAccessTime(quiz.getFirstAccessTime())
                        .firstAnswerTime(quiz.getFirstAnswerTime())
                        .getFirstHintTime(quiz.getFirstHintTime())
                        .getSecondHintTime(quiz.getSecondHintTime())
                        .getAnswerTime(quiz.getGetAnswerTime())
                        .build())
                .collect(Collectors.toList());
    }

    private List<QuizInfoDto> findQuiz3Infos() {
        List<Quiz3Ep03> quizzes = ep3Quiz3Repository.findAll();
        return quizzes.stream().map(quiz -> QuizInfoDto.builder()
                        .code(quiz.getUserId().getCode())
                        .placeNumber(3)
                        .quizNumber(quiz.getQuizNumber())
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
        List<WrongAnswerEp03> wrongAnswers = ep3WrongAnswerRepository.findAll();
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
