package com.ablez.admin.info.controller;

import com.ablez.admin.info.service.InfoService;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/infos")
@Validated
public class InfoController {
    private final InfoService infoService;

    @GetMapping("/users")
    public ResponseEntity getUserInfoFromDb(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + URLEncoder.encode("유저 정보.xlsx", StandardCharsets.UTF_8) + "\"");

        Workbook workbook = infoService.getUserInfos();
        workbook.write(response.getOutputStream());
        workbook.close();

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/quizzes")
    public ResponseEntity getQuizInfoFromDb(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + URLEncoder.encode("퀴즈 로그 정보.xlsx", StandardCharsets.UTF_8) + "\"");

        Workbook workbook = infoService.getQuizInfos();
        workbook.write(response.getOutputStream());
        workbook.close();

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/answers")
    public ResponseEntity getWrongAnswerInfoFromDb(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + URLEncoder.encode("오답 정보.xlsx", StandardCharsets.UTF_8) + "\"");

        Workbook workbook = infoService.getWrongAnswerInfos();
        workbook.write(response.getOutputStream());
        workbook.close();

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/infos")
    public ResponseEntity makeUserInfos() {
        infoService.makeUserInfos();
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/shift")
    public ResponseEntity transferDatabase() {
        infoService.transferDatabase();
        return new ResponseEntity(HttpStatus.OK);
    }
}
