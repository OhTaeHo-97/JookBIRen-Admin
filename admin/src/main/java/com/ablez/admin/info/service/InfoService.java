package com.ablez.admin.info.service;

import com.ablez.admin.quiz.service.QuizService;
import com.ablez.admin.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class InfoService {
    private final UserService userService;
    private final QuizService quizService;

    @Transactional(readOnly = true)
    public Workbook getUserInfos() {
        Workbook workbook = new XSSFWorkbook();
        userService.makeUserInfoEp01(workbook);
        userService.makeUserInfoEp02(workbook);
        userService.makeUserInfoEp03(workbook);
        return workbook;
    }

    @Transactional(readOnly = true)
    public Workbook getQuizInfos() {
        Workbook workbook = new XSSFWorkbook();
        quizService.getAllQuizInfosEp01(workbook);
        quizService.getAllQuizInfosEp02(workbook);
        quizService.getAllQuizInfosEp03(workbook);
        return workbook;
    }

    @Transactional(readOnly = true)
    public Workbook getWrongAnswerInfos() {
        Workbook workbook = new XSSFWorkbook();
        quizService.getWrongAnswerInfosEp01(workbook);
        quizService.getWrongAnswerInfosEp02(workbook);
        quizService.getWrongAnswerInfosEp03(workbook);
        return workbook;
    }
}
