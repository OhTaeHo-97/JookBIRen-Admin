package com.ablez.admin.quiz.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class QuizDto {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class QuizInfoDto {
        // 코드, 장소 번호, 퀴즈 번호, 처음 접속 시간, 처음 정답 맞춘 시간, 첫 힌트 본 시간, 두 번째 힌트 본 시간, 정답 본 시간
        private String code;
        private int placeNumber;
        private int quizNumber;
        private LocalDateTime firstAccessTime;
        private LocalDateTime firstAnswerTime;
        private LocalDateTime getFirstHintTime;
        private LocalDateTime getSecondHintTime;
        private LocalDateTime getAnswerTime;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class WrongAnswerDto {
        // 코드, 장소 번호, 퀴즈 번호, 잘못된 정답, 시간
        private String code;
        private int placeNumber;
        private int quizNumber;
        private String answer;
        private LocalDateTime time;
    }
}
