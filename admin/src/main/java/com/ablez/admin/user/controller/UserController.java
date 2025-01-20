package com.ablez.admin.user.controller;

import com.ablez.admin.user.service.UserService;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/users")
@Validated
public class UserController {
    private final UserService userService;

    @GetMapping("/chatbot")
    public ResponseEntity registerCodesInEp0(@Positive Integer count, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + URLEncoder.encode(String.format("챗봇 코드 정보-%s.xlsx", getCurrentTime()),
                        StandardCharsets.UTF_8) + "\"");
        Workbook workbook = userService.registerCodesInEp0(count);
        workbook.write(response.getOutputStream());
        workbook.close();

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/web")
    public ResponseEntity registerCodesInJookBiRen(@RequestParam("file") MultipartFile file) throws IOException {
        userService.registerCodesInJookBiRen(file);
        return new ResponseEntity(HttpStatus.OK);
    }

    private String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        String time = String.format("%d%02d%02d", now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        return time;
    }
}
