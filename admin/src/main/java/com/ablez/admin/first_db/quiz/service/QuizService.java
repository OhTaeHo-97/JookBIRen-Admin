package com.ablez.admin.first_db.quiz.service;

import com.ablez.admin.first_db.quiz.dto.QuizDto.QuizInfoDto;
import com.ablez.admin.first_db.quiz.dto.QuizDto.WrongAnswerDto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class QuizService {
    private final Ep01QuizInfoService ep01QuizInfoService;
    private final Ep02QuizInfoService ep02QuizInfoService;
    private final Ep03QuizInfoService ep03QuizInfoService;

    public void getAllQuizInfosEp01(Workbook workbook) {
        List<QuizInfoDto> quizzes = ep01QuizInfoService.findAllQuizInfos();

        Sheet sheet = workbook.createSheet("에피소드1 퀴즈 정보");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("코드");
        row.createCell(1).setCellValue("장소 번호");
        row.createCell(2).setCellValue("퀴즈 번호");
        row.createCell(3).setCellValue("첫 접속 시간");
        row.createCell(4).setCellValue("정답 시간");
        row.createCell(5).setCellValue("첫 번째 힌트 본 시간");
        row.createCell(6).setCellValue("두 번째 힌트 본 시간");
        row.createCell(7).setCellValue("정답 본 시간");

        for (int quizIdx = 0; quizIdx < quizzes.size(); quizIdx++) {
            row = sheet.createRow(quizIdx + 1);
            QuizInfoDto quiz = quizzes.get(quizIdx);

            row.createCell(0).setCellValue(quiz.getCode());
            row.createCell(1).setCellValue(quiz.getPlaceNumber());
            row.createCell(2).setCellValue(quiz.getQuizNumber());
            row.createCell(3).setCellValue(parseLocalDateTime(quiz.getFirstAccessTime()));
            row.createCell(4).setCellValue(parseLocalDateTime(quiz.getFirstAnswerTime()));
            row.createCell(5).setCellValue(parseLocalDateTime(quiz.getGetFirstHintTime()));
            row.createCell(6).setCellValue(parseLocalDateTime(quiz.getGetSecondHintTime()));
            row.createCell(7).setCellValue(parseLocalDateTime(quiz.getGetAnswerTime()));
        }
    }

    public void getAllQuizInfosEp02(Workbook workbook) {
        List<QuizInfoDto> quizzes = ep02QuizInfoService.findAllQuizInfos();

        Sheet sheet = workbook.createSheet("에피소드2 퀴즈 정보");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("코드");
        row.createCell(1).setCellValue("장소 번호");
        row.createCell(2).setCellValue("퀴즈 번호");
        row.createCell(3).setCellValue("첫 접속 시간");
        row.createCell(4).setCellValue("정답 시간");
        row.createCell(5).setCellValue("첫 번째 힌트 본 시간");
        row.createCell(6).setCellValue("두 번째 힌트 본 시간");
        row.createCell(7).setCellValue("정답 본 시간");

        for (int quizIdx = 0; quizIdx < quizzes.size(); quizIdx++) {
            row = sheet.createRow(quizIdx + 1);
            QuizInfoDto quiz = quizzes.get(quizIdx);

            row.createCell(0).setCellValue(quiz.getCode());
            row.createCell(1).setCellValue(quiz.getPlaceNumber());
            row.createCell(2).setCellValue(quiz.getQuizNumber());
            row.createCell(3).setCellValue(parseLocalDateTime(quiz.getFirstAccessTime()));
            row.createCell(4).setCellValue(parseLocalDateTime(quiz.getFirstAnswerTime()));
            row.createCell(5).setCellValue(parseLocalDateTime(quiz.getGetFirstHintTime()));
            row.createCell(6).setCellValue(parseLocalDateTime(quiz.getGetSecondHintTime()));
            row.createCell(7).setCellValue(parseLocalDateTime(quiz.getGetAnswerTime()));
        }
    }

    public void getAllQuizInfosEp03(Workbook workbook) {
        List<QuizInfoDto> quizzes = ep03QuizInfoService.findAllQuizInfos();

        Sheet sheet = workbook.createSheet("에피소드3 퀴즈 정보");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("코드");
        row.createCell(1).setCellValue("장소 번호");
        row.createCell(2).setCellValue("퀴즈 번호");
        row.createCell(3).setCellValue("첫 접속 시간");
        row.createCell(4).setCellValue("정답 시간");
        row.createCell(5).setCellValue("첫 번째 힌트 본 시간");
        row.createCell(6).setCellValue("두 번째 힌트 본 시간");
        row.createCell(7).setCellValue("정답 본 시간");

        for (int quizIdx = 0; quizIdx < quizzes.size(); quizIdx++) {
            row = sheet.createRow(quizIdx + 1);
            QuizInfoDto quiz = quizzes.get(quizIdx);

            row.createCell(0).setCellValue(quiz.getCode());
            row.createCell(1).setCellValue(quiz.getPlaceNumber());
            row.createCell(2).setCellValue(quiz.getQuizNumber());
            row.createCell(3).setCellValue(parseLocalDateTime(quiz.getFirstAccessTime()));
            row.createCell(4).setCellValue(parseLocalDateTime(quiz.getFirstAnswerTime()));
            row.createCell(5).setCellValue(parseLocalDateTime(quiz.getGetFirstHintTime()));
            row.createCell(6).setCellValue(parseLocalDateTime(quiz.getGetSecondHintTime()));
            row.createCell(7).setCellValue(parseLocalDateTime(quiz.getGetAnswerTime()));
        }
    }

    public void getWrongAnswerInfosEp01(Workbook workbook) {
        List<WrongAnswerDto> wrongAnswers = ep01QuizInfoService.findAllWrongAnswerInfos();

        Sheet sheet = workbook.createSheet("에피소드1 오답 정보");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("코드");
        row.createCell(1).setCellValue("장소 번호");
        row.createCell(2).setCellValue("퀴즈 번호");
        row.createCell(3).setCellValue("오답");
        row.createCell(4).setCellValue("시간");

        for (int wrongAnswerIdx = 0; wrongAnswerIdx < wrongAnswers.size(); wrongAnswerIdx++) {
            row = sheet.createRow(wrongAnswerIdx + 1);
            WrongAnswerDto wrongAnswer = wrongAnswers.get(wrongAnswerIdx);

            row.createCell(0).setCellValue(wrongAnswer.getCode());
            row.createCell(1).setCellValue(wrongAnswer.getPlaceNumber());
            row.createCell(2).setCellValue(wrongAnswer.getQuizNumber());
            row.createCell(3).setCellValue(wrongAnswer.getAnswer());
            row.createCell(4).setCellValue(parseLocalDateTime(wrongAnswer.getTime()));
        }
    }

    public void getWrongAnswerInfosEp02(Workbook workbook) {
        List<WrongAnswerDto> wrongAnswers = ep02QuizInfoService.findAllWrongAnswerInfos();

        Sheet sheet = workbook.createSheet("에피소드2 오답 정보");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("코드");
        row.createCell(1).setCellValue("장소 번호");
        row.createCell(2).setCellValue("퀴즈 번호");
        row.createCell(3).setCellValue("오답");
        row.createCell(4).setCellValue("시간");

        for (int wrongAnswerIdx = 0; wrongAnswerIdx < wrongAnswers.size(); wrongAnswerIdx++) {
            row = sheet.createRow(wrongAnswerIdx + 1);
            WrongAnswerDto wrongAnswer = wrongAnswers.get(wrongAnswerIdx);

            row.createCell(0).setCellValue(wrongAnswer.getCode());
            row.createCell(1).setCellValue(wrongAnswer.getPlaceNumber());
            row.createCell(2).setCellValue(wrongAnswer.getQuizNumber());
            row.createCell(3).setCellValue(wrongAnswer.getAnswer());
            row.createCell(4).setCellValue(parseLocalDateTime(wrongAnswer.getTime()));
        }
    }

    public void getWrongAnswerInfosEp03(Workbook workbook) {
        List<WrongAnswerDto> wrongAnswers = ep03QuizInfoService.findAllWrongAnswerInfos();

        Sheet sheet = workbook.createSheet("에피소드3 오답 정보");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("코드");
        row.createCell(1).setCellValue("장소 번호");
        row.createCell(2).setCellValue("퀴즈 번호");
        row.createCell(3).setCellValue("오답");
        row.createCell(4).setCellValue("시간");

        for (int wrongAnswerIdx = 0; wrongAnswerIdx < wrongAnswers.size(); wrongAnswerIdx++) {
            row = sheet.createRow(wrongAnswerIdx + 1);
            WrongAnswerDto wrongAnswer = wrongAnswers.get(wrongAnswerIdx);

            row.createCell(0).setCellValue(wrongAnswer.getCode());
            row.createCell(1).setCellValue(wrongAnswer.getPlaceNumber());
            row.createCell(2).setCellValue(wrongAnswer.getQuizNumber());
            row.createCell(3).setCellValue(wrongAnswer.getAnswer());
            row.createCell(4).setCellValue(parseLocalDateTime(wrongAnswer.getTime()));
        }
    }

    private String parseLocalDateTime(LocalDateTime time) {
        if (time == null) {
            return "";
        }
        return time.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
    }
}
