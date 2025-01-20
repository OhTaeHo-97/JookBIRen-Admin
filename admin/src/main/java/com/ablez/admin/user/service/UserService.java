package com.ablez.admin.user.service;

import com.ablez.admin.user.entity.UserEp00;
import com.ablez.admin.user.entity.UserEp01;
import com.ablez.admin.user.entity.UserEp02;
import com.ablez.admin.user.entity.UserInfoEp01;
import com.ablez.admin.user.entity.UserInfoEp02;
import com.ablez.admin.user.repository.UserEp00JpaRepository;
import com.ablez.admin.user.repository.UserEp00Repository;
import com.ablez.admin.user.repository.UserEp01Repository;
import com.ablez.admin.user.repository.UserEp02Repository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private static final int CODE_LENGTH = 6;
    private static final int EXCEL_COLUMN_LENGTH = 3;

    private final UserEp01Repository userEp01Repository;
    private final UserEp02Repository userEp02Repository;
    private final UserEp00Repository userEp00Repository;
    private final UserEp00JpaRepository userEp00JpaRepository;
    private final UserInfoService userInfoService;

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
        return code;
    }

    public void registerCodesInJookBiRen(MultipartFile file) throws IOException {
        try {
            Workbook workbook = readExcel(file);
            // 엑셀 파일에서 첫 번째 시트 불러오기
            Sheet worksheet = workbook.getSheetAt(0);

            for (int rowIdx = 0; rowIdx < worksheet.getPhysicalNumberOfRows(); rowIdx++) {
                Row row = worksheet.getRow(rowIdx);
                if (row != null) {
                    List<String> infos = processExcelData(row);
                    if (infos.isEmpty()) {
                        return;
                    }

                    String phone = infos.get(0);
                    int amount = Integer.parseInt(infos.get(1));
                    int episode = Integer.parseInt(infos.get(2));
                    for (int count = 0; count < amount; count++) {
                        makeUser(episode, phone);
                    }
                }
            }
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("엑셀 파일을 읽는 중 오류가 발생했습니다.");
        } catch (NumberFormatException e) {
            throw new RuntimeException("엑셀 파일의 데이터를 처리하는 중 오류가 발생했습니다.");
        }
    }

    private void makeUser(int episode, String phone) {
        if (episode == 1) {
            UserInfoEp01 userInfo = userInfoService.makeUserInfoEp01(phone);
            UserEp01 user = new UserEp01(userInfo.getCode(), userInfo);
            userEp01Repository.save(user);

        } else if (episode == 2) {
            UserInfoEp02 userInfo = userInfoService.makeUserInfoEp02(phone);
            UserEp02 user = new UserEp02(userInfo.getCode(), userInfo);
            userEp02Repository.save(user);
        }
    }

    private Workbook readExcel(MultipartFile file) throws IOException {
        String fileExtension = findFileExtension(file);
        Workbook workbook = null;
        try {
            // 엑셀 파일 읽기
            workbook = makeWorkbook(fileExtension, file);
        } catch (IOException e) {
            throw new RuntimeException("엑셀 파일을 읽는 중 오류가 발생했습니다.");
        }
        return workbook;
    }

    private List<String> processExcelData(Row row) {
        List<String> infos = new ArrayList<>();
        for (int colIdx = 0; colIdx < EXCEL_COLUMN_LENGTH; colIdx++) {
            Cell cell = row.getCell(colIdx);
            if (cell == null) {
                continue;
            }
            String cellData = processCellData(cell);
            if (cellData.isEmpty()) {
                return new ArrayList<>();
            }

            infos.add(cellData);
        }
        return infos;
    }

    private String processCellData(Cell cell) {
        if (cell.getCellType() == CellType.FORMULA) {
            return cell.getCellFormula();
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            return processNumericData(cell);
        }
        if (cell.getCellType() == CellType.STRING || cell.getCellType() == CellType.BLANK
                || cell.getCellType() == CellType.ERROR) {
            return cell.getStringCellValue() + "";
        }
        return cell.getStringCellValue();
    }

    private String processNumericData(Cell cell) {
        double numericCellValue = cell.getNumericCellValue();
        if (numericCellValue == Math.rint(numericCellValue)) {
            return String.valueOf((int) numericCellValue);
        } else {
            return String.valueOf(numericCellValue);
        }
    }

    private String findFileExtension(MultipartFile file) {
        return FilenameUtils.getExtension(file.getOriginalFilename());
    }

    private Workbook makeWorkbook(String fileExtension, MultipartFile file) throws IOException {
        if (fileExtension.equals("xls")) {
            return new HSSFWorkbook(file.getInputStream());
        } else {
            return new XSSFWorkbook(file.getInputStream());
        }
    }
}
