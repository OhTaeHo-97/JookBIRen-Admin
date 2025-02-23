package com.ablez.admin.first_db.user.service;

import com.ablez.admin.first_db.security.entity.Authority;
import com.ablez.admin.first_db.user.entity.UserEp00;
import com.ablez.admin.first_db.user.entity.UserEp01;
import com.ablez.admin.first_db.user.entity.UserEp02;
import com.ablez.admin.first_db.user.entity.UserEp03;
import com.ablez.admin.first_db.user.entity.UserInfo;
import com.ablez.admin.first_db.user.repository.UserEp00Repository;
import com.ablez.admin.first_db.user.repository.UserEp01JpaRepository;
import com.ablez.admin.first_db.user.repository.UserEp01Repository;
import com.ablez.admin.first_db.user.repository.UserEp02JpaRepository;
import com.ablez.admin.first_db.user.repository.UserEp02Repository;
import com.ablez.admin.first_db.user.repository.UserEp03Repository;
import com.ablez.admin.first_db.user.repository.UserInfoJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserService {
    private static final int CODE_LENGTH = 6;
//    private static final int EXCEL_COLUMN_LENGTH = 3;

    private final UserEp00Repository userEp00Repository;
    //    private final UserEp00JpaRepository userEp00JpaRepository;
    private final UserEp01Repository userEp01Repository;
    private final UserEp01JpaRepository userEp01JpaRepository;
    private final UserEp02Repository userEp02Repository;
    private final UserEp02JpaRepository userEp02JpaRepository;
    private final UserEp03Repository userEp03Repository;
    //    private final UserEp03JpaRepository userEp03JpaRepository;
//    private final UserInfoService userInfoService;
//    private final SmsService smsService;
    private final UserInfoJpaRepository userInfoJpaRepository;

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
        UserInfo userInfo = new UserInfo(code);
        userInfoJpaRepository.save(userInfo);
        Authority authority = new Authority("ROLE_USER", userInfo);
        userInfo.addRole(authority);
        UserEp00 userEp00 = new UserEp00(code, userInfo);
//        userInfo.setUserEp00(userEp00);
//        userEp00JpaRepository.save(userEp00);
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

    public Workbook registerCodesInWeb(int episode, int count) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(String.format("에피소드%d 코드 정보", episode));
        for (int user = 0; user < count; user++) {
            Row row = sheet.createRow(user);
            String code = registerCodeInWeb(episode);
            row.createCell(0).setCellValue(code);
        }
        return workbook;
    }

    private String registerCodeInWeb(int episode) {
        String code = generateCodeInWeb(episode);
        if (episode == 1) {
            UserInfo userInfo = new UserInfo(code);
            userInfoJpaRepository.save(userInfo);
            Authority authority = new Authority("ROLE_USER", userInfo);
            userInfo.addRole(authority);
            UserEp01 userEp01 = new UserEp01(code, userInfo);
//            userInfo.setUserEp01(userEp01);
//            UserEp01 userEp01 = new UserEp01(code);
//            userEp01JpaRepository.save(userEp01);
        } else if (episode == 2) {
            UserInfo userInfo = new UserInfo(code);
            userInfoJpaRepository.save(userInfo);
            Authority authority = new Authority("ROLE_USER", userInfo);
            userInfo.addRole(authority);
            UserEp02 userEp02 = new UserEp02(code, userInfo);
//            userInfo.setUserEp02(userEp02);
//            UserEp02 userEp02 = new UserEp02(code);
//            userEp02JpaRepository.save(userEp02);
        } else if (episode == 3) {
            UserInfo userInfo = new UserInfo(code);
            userInfoJpaRepository.save(userInfo);
            Authority authority = new Authority("ROLE_USER", userInfo);
            userInfo.addRole(authority);
            UserEp03 userEp03 = new UserEp03(code, userInfo);
//            UserEp03 userEp03 = new UserEp03(code);
//            userEp03JpaRepository.save(userEp03);
        }
        return code;
    }

    private String generateCodeInWeb(int episode) {
        String code = makeCode(episode);
        if (episode == 1) {
            while (userEp01Repository.findByCode(code).isPresent()) {
                code = makeCode(episode);
            }
        } else if (episode == 2) {
            while (userEp02Repository.findByCode(code).isPresent()) {
                code = makeCode(episode);
            }
        } else if (episode == 3) {
            while (userEp03Repository.findByCode(code).isPresent()) {
                code = makeCode(episode);
            }
        }
        return code;
    }

    public Workbook makeUserInfoEp01(Workbook workbook) {
        // 이름, 코드, 전화번호, 플랫폼, 닉네임, 주문번호, 금액, 에피소드, 주소, 생성일
        List<UserEp01> users = userEp01Repository.findAll();

        Sheet sheet = workbook.createSheet("에피소드1 유저 정보");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("코드");
        row.createCell(1).setCellValue("에피소드");
        row.createCell(2).setCellValue("생성일");
        row.createCell(3).setCellValue("정답 시간");
        row.createCell(4).setCellValue("용의자");
        row.createCell(5).setCellValue("첫 로그인 시간");
        row.createCell(6).setCellValue("푼 문제 개수");
        row.createCell(7).setCellValue("탐정 점수");

        for (int userIdx = 0; userIdx < users.size(); userIdx++) {
            row = sheet.createRow(userIdx + 1);
            UserEp01 user = users.get(userIdx);

            row.createCell(0).setCellValue(user.getCode());
            row.createCell(1).setCellValue(1);
            row.createCell(2).setCellValue("");
            row.createCell(3).setCellValue(user.getAnswerTime());
            row.createCell(4).setCellValue(user.getCriminal());
            row.createCell(5).setCellValue(user.getFirstLoginTime());
            row.createCell(6).setCellValue(user.getSolvedQuizCount());
            row.createCell(7).setCellValue(user.getScore());
        }

        return workbook;
    }

    public Workbook makeUserInfoEp02(Workbook workbook) {
        // 이름, 코드, 전화번호, 플랫폼, 닉네임, 주문번호, 금액, 에피소드, 주소, 생성일
        List<UserEp02> users = userEp02Repository.findAll();

        Sheet sheet = workbook.createSheet("에피소드2 유저 정보");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("코드");
        row.createCell(1).setCellValue("에피소드");
        row.createCell(2).setCellValue("생성일");
        row.createCell(3).setCellValue("정답 시간");
        row.createCell(4).setCellValue("용의자1");
        row.createCell(5).setCellValue("용의자2");
        row.createCell(6).setCellValue("첫 로그인 시간");
        row.createCell(7).setCellValue("푼 문제 개수");
        row.createCell(8).setCellValue("탐정 점수");

        for (int userIdx = 0; userIdx < users.size(); userIdx++) {
            row = sheet.createRow(userIdx + 1);
            UserEp02 user = users.get(userIdx);

            row.createCell(0).setCellValue(user.getCode());
            row.createCell(1).setCellValue(2);
            row.createCell(2).setCellValue("");
            row.createCell(3).setCellValue(user.getAnswerTime());
            row.createCell(4).setCellValue(user.getCriminal1());
            row.createCell(5).setCellValue(user.getCriminal2());
            row.createCell(6).setCellValue(user.getFirstLoginTime());
            row.createCell(7).setCellValue(user.getSolvedQuizCount());
            row.createCell(8).setCellValue(user.getScore());
        }

        return workbook;
    }

    public Workbook makeUserInfoEp03(Workbook workbook) {
        // 이름, 코드, 전화번호, 플랫폼, 닉네임, 주문번호, 금액, 에피소드, 주소, 생성일
        List<UserEp03> users = userEp03Repository.findAll();

        Sheet sheet = workbook.createSheet("에피소드1 유저 정보");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("코드");
        row.createCell(1).setCellValue("에피소드");
        row.createCell(2).setCellValue("생성일");
        row.createCell(3).setCellValue("정답 시간");
        row.createCell(4).setCellValue("용의자");
        row.createCell(5).setCellValue("첫 로그인 시간");
        row.createCell(6).setCellValue("푼 문제 개수");
        row.createCell(7).setCellValue("탐정 점수");

        for (int userIdx = 0; userIdx < users.size(); userIdx++) {
            row = sheet.createRow(userIdx + 1);
            UserEp03 user = users.get(userIdx);

            row.createCell(0).setCellValue(user.getCode());
            row.createCell(1).setCellValue(3);
            row.createCell(2).setCellValue("");
            row.createCell(3).setCellValue(user.getAnswerTime());
            row.createCell(4).setCellValue(user.getCriminal());
            row.createCell(5).setCellValue(user.getFirstLoginTime());
            row.createCell(6).setCellValue(user.getSolvedQuizCount());
            row.createCell(7).setCellValue(user.getScore());
        }

        return workbook;
    }
//    public void registerCodesInJookBiRen(MultipartFile file) throws IOException {
//        try {
//            Workbook workbook = readExcel(file);
//            // 엑셀 파일에서 첫 번째 시트 불러오기
//            Sheet worksheet = workbook.getSheetAt(0);
//
//            for (int rowIdx = 0; rowIdx < worksheet.getPhysicalNumberOfRows(); rowIdx++) {
//                Row row = worksheet.getRow(rowIdx);
//                if (row != null) {
//                    List<String> codes = new ArrayList<>();
//                    List<String> infos = processExcelData(row);
//                    if (infos.isEmpty()) {
//                        return;
//                    }
//
//                    String phone = infos.get(0);
//                    int amount = Integer.parseInt(infos.get(1));
//                    int episode = Integer.parseInt(infos.get(2));
//                    for (int count = 0; count < amount; count++) {
//                        makeUser(episode, phone, codes);
//                    }
//
//                    // SMS 전송
//                    String content = "";
//                    for (String code : codes) {
//                        content += code + "\n";
//                    }
////                    smsService.sendMessage("코드 생성 완료했습니다. 코드는 다음과 같습니다.\n" + content, new SmsSendDto(phone));
//                    sendMessage(episode, content, phone);
//                }
//            }
//            workbook.close();
//        } catch (IOException e) {
//            throw new RuntimeException("엑셀 파일을 읽는 중 오류가 발생했습니다.");
//        } catch (NumberFormatException e) {
//            throw new RuntimeException("엑셀 파일의 데이터를 처리하는 중 오류가 발생했습니다.");
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException("SMS 전송 중 오류가 발생했습니다.");
//        } catch (URISyntaxException e) {
//            throw new RuntimeException("SMS 전송 중 오류가 발생했습니다.");
//        } catch (InvalidKeyException e) {
//            throw new RuntimeException("SMS 전송 중 오류가 발생했습니다.");
//        }
//    }

//    private void sendMessage(int episode, String codes, String phone)
//            throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException {
//        int month = findCurrentMonth();
//        if (episode == 1) {
//            if (month >= 11 || month <= 3) {
//                smsService.sendMessage(String.format(SMS_EPISODE1_MESSAGE_WINTER, codes), new SmsSendDto(phone));
//            } else if (month >= 6 && month <= 8) {
//                smsService.sendMessage(String.format(SMS_EPISODE1_MESSAGE_SUMMER, codes), new SmsSendDto(phone));
//            } else {
//                smsService.sendMessage(String.format(SMS_EPISODE1_MESSAGE_SPRING, codes), new SmsSendDto(phone));
//            }
//        } else if (episode == 2) {
//            if (month >= 11 || month <= 3) {
//                smsService.sendMessage(String.format(SMS_EPISODE2_MESSAGE_WINTER, codes), new SmsSendDto(phone));
//            } else if (month >= 6 && month <= 8) {
//                smsService.sendMessage(String.format(SMS_EPISODE2_MESSAGE_SUMMER, codes), new SmsSendDto(phone));
//            } else {
//                smsService.sendMessage(String.format(SMS_EPISODE2_MESSAGE_SPRING, codes), new SmsSendDto(phone));
//            }
//        }
////        smsService.sendMessage(String.format(SMS_CHATBOT_MESSAGE, "link", codes), new SmsSendDto(phone));
////        smsService.sendMessage("코드 생성 완료했습니다. 코드는 다음과 같습니다.\n" + codes, new SmsSendDto(phone));
//    }

//    private int findCurrentMonth() {
//        LocalDateTime now = LocalDateTime.now();
//        return now.getMonthValue();
//    }

//    private void makeUser(int episode, String phone, List<String> codes) {
//        if (episode == 1) {
//            UserInfoEp01 userInfo = userInfoService.makeUserInfoEp01(phone);
//            UserEp01 user = new UserEp01(userInfo.getCode(), userInfo);
//            userEp01JpaRepository.save(user);
//            codes.add(userInfo.getCode());
//        } else if (episode == 2) {
//            UserInfoEp02 userInfo = userInfoService.makeUserInfoEp02(phone);
//            UserEp02 user = new UserEp02(userInfo.getCode(), userInfo);
//            userEp02JpaRepository.save(user);
//            codes.add(userInfo.getCode());
//        }
//    }

//    private Workbook readExcel(MultipartFile file) throws IOException {
//        String fileExtension = findFileExtension(file);
//        Workbook workbook = null;
//        try {
//            // 엑셀 파일 읽기
//            workbook = makeWorkbook(fileExtension, file);
//        } catch (IOException e) {
//            throw new RuntimeException("엑셀 파일을 읽는 중 오류가 발생했습니다.");
//        }
//        return workbook;
//    }

//    private List<String> processExcelData(Row row) {
//        List<String> infos = new ArrayList<>();
//        for (int colIdx = 0; colIdx < EXCEL_COLUMN_LENGTH; colIdx++) {
//            Cell cell = row.getCell(colIdx);
//            if (cell == null) {
//                continue;
//            }
//            String cellData = processCellData(cell);
//            if (cellData.isEmpty()) {
//                return new ArrayList<>();
//            }
//
//            infos.add(cellData);
//        }
//        return infos;
//    }

//    private String processCellData(Cell cell) {
//        if (cell.getCellType() == CellType.FORMULA) {
//            return cell.getCellFormula();
//        }
//        if (cell.getCellType() == CellType.NUMERIC) {
//            return processNumericData(cell);
//        }
//        if (cell.getCellType() == CellType.STRING || cell.getCellType() == CellType.BLANK
//                || cell.getCellType() == CellType.ERROR) {
//            return cell.getStringCellValue() + "";
//        }
//        return cell.getStringCellValue();
//    }

//    private String processNumericData(Cell cell) {
//        double numericCellValue = cell.getNumericCellValue();
//        if (numericCellValue == Math.rint(numericCellValue)) {
//            return String.valueOf((int) numericCellValue);
//        } else {
//            return String.valueOf(numericCellValue);
//        }
//    }

//    private String findFileExtension(MultipartFile file) {
//        return FilenameUtils.getExtension(file.getOriginalFilename());
//    }

//    private Workbook makeWorkbook(String fileExtension, MultipartFile file) throws IOException {
//        if (fileExtension.equals("xls")) {
//            return new HSSFWorkbook(file.getInputStream());
//        } else {
//            return new XSSFWorkbook(file.getInputStream());
//        }
//    }

    public void makeChatbotUserInfos() {
        List<UserEp00> users = userEp00Repository.findAll();
        users.stream().forEach(user -> {
            UserInfo userInfo = new UserInfo(user.getCode());
            userInfo.setUserEp00(user);
            userInfoJpaRepository.save(userInfo);
            userInfo.addRole(new Authority("ROLE_USER", userInfo));
            user.setUserInfo(userInfo);
        });
    }

    public void makeEp1UserInfos() {
        List<UserEp01> users = userEp01JpaRepository.findAll();
        users.stream().forEach(user -> {
            UserInfo userInfo = new UserInfo(user.getCode());
            userInfo.setUserEp01(user);
            userInfoJpaRepository.save(userInfo);
            userInfo.addRole(new Authority("ROLE_USER", userInfo));
            user.setUserInfo(userInfo);
        });
    }

    public void makeEp2UserInfos() {
        List<UserEp02> users = userEp02JpaRepository.findAll();
        users.stream().forEach(user -> {
            UserInfo userInfo = new UserInfo(user.getCode());
            userInfo.setUserEp02(user);
            userInfoJpaRepository.save(userInfo);
            userInfo.addRole(new Authority("ROLE_USER", userInfo));
            user.setUserInfo(userInfo);
        });
    }

    public void makeEp3UserInfos() {
        List<UserEp03> users = userEp03Repository.findAll();
        users.stream().forEach(user -> {
            UserInfo userInfo = new UserInfo(user.getCode());
            userInfo.setUserEp03(user);
            userInfoJpaRepository.save(userInfo);
            userInfo.addRole(new Authority("ROLE_USER", userInfo));
            user.setUserInfo(userInfo);
        });
    }
}
