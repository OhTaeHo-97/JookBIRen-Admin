package com.ablez.admin.user.service;

import com.ablez.admin.user.entity.UserEp00;
import com.ablez.admin.user.repository.UserEp00JpaRepository;
import com.ablez.admin.user.repository.UserEp00Repository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private static final int CODE_LENGTH = 6;

    private final UserEp00Repository userEp00Repository;
    private final UserEp00JpaRepository userEp00JpaRepository;

    public Workbook registerCodesInEp0(int count) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("코드 정보");
        for (int user = 0; user < count; user++) {
            Row row = sheet.createRow(user);
            String code = registerCodeInEp0();
            row.createCell(0).setCellValue(code);
        }
        return workbook;
    }

    private String registerCodeInEp0() {
        String code = generateCode(0);
        UserEp00 userEp00 = new UserEp00(code);
        userEp00JpaRepository.save(userEp00);
        return code;
    }

    private String generateCode(int episode) {
        String code = makeCode(episode);
        while (userEp00Repository.findByCode(code).isPresent()) {
            code = makeCode(episode);
        }
        return code;
    }

    private String makeCode(int episode) {
        String code = String.format("EP%d", episode) + RandomStringUtils.randomAlphanumeric(CODE_LENGTH).toUpperCase();
//        String code = "EP" + episode;
//        code += RandomStringUtils.randomAlphanumeric(CODE_LENGTH);
//        code = code.toUpperCase();
        return code;
    }
}
