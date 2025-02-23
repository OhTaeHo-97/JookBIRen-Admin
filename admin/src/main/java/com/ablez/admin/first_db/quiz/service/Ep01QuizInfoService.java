package com.ablez.admin.first_db.quiz.service;

import com.ablez.admin.first_db.quiz.dto.QuizDto.QuizInfoDto;
import com.ablez.admin.first_db.quiz.dto.QuizDto.WrongAnswerDto;
import com.ablez.admin.first_db.quiz.repository.episode1.Ep1Quiz0Repository;
import com.ablez.admin.first_db.quiz.repository.episode1.Ep1Quiz1Repository;
import com.ablez.admin.first_db.quiz.repository.episode1.Ep1Quiz4Repository;
import com.ablez.admin.first_db.quiz.repository.episode1.Ep1WrongAnswerRepository;
import com.ablez.admin.first_db.quiz.entity.episode1.Quiz0Ep01;
import com.ablez.admin.first_db.quiz.entity.episode1.Quiz1Ep01;
import com.ablez.admin.first_db.quiz.entity.episode1.Quiz2Ep01;
import com.ablez.admin.first_db.quiz.entity.episode1.Quiz3Ep01;
import com.ablez.admin.first_db.quiz.entity.episode1.Quiz4Ep01;
import com.ablez.admin.first_db.quiz.entity.episode1.WrongAnswerEp01;
import com.ablez.admin.first_db.quiz.repository.episode1.Ep1Quiz2Repository;
import com.ablez.admin.first_db.quiz.repository.episode1.Ep1Quiz3Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class Ep01QuizInfoService {
    private final Ep1Quiz0Repository ep1Quiz0Repository;
    private final Ep1Quiz1Repository ep1Quiz1Repository;
    private final Ep1Quiz2Repository ep1Quiz2Repository;
    private final Ep1Quiz3Repository ep1Quiz3Repository;
    private final Ep1Quiz4Repository ep1Quiz4Repository;
    private final Ep1WrongAnswerRepository ep1WrongAnswerRepository;

    public List<QuizInfoDto> findAllQuizInfos() {
        List<QuizInfoDto> quizzes = new ArrayList<>();
        quizzes.addAll(findQuiz0Infos());
        quizzes.addAll(findQuiz1Infos());
        quizzes.addAll(findQuiz2Infos());
        quizzes.addAll(findQuiz3Infos());
        quizzes.addAll(findQuiz4Infos());
        return quizzes;
    }

    private List<QuizInfoDto> findQuiz0Infos() {
        List<Quiz0Ep01> quizzes = ep1Quiz0Repository.findAll();
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

    private List<QuizInfoDto> findQuiz1Infos() {
        List<Quiz1Ep01> quizzes = ep1Quiz1Repository.findAll();
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

    private List<QuizInfoDto> findQuiz2Infos() {
        List<Quiz2Ep01> quizzes = ep1Quiz2Repository.findAll();
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
        List<Quiz3Ep01> quizzes = ep1Quiz3Repository.findAll();
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

    private List<QuizInfoDto> findQuiz4Infos() {
        List<Quiz4Ep01> quizzes = ep1Quiz4Repository.findAll();
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
        List<WrongAnswerEp01> wrongAnswers = ep1WrongAnswerRepository.findAll();
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
