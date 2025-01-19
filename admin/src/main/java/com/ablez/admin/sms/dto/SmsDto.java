package com.ablez.admin.sms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SmsDto {
    // 인증번호 전송할 휴대폰 번호 DTO
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SmsSendDto {
        private String to;
    }

    // 네이버 SMS API 요청시 필요한 데이터
    @Getter
    @AllArgsConstructor
    @Builder
    public static class SmsRequestDto {
        private String type;
        private String contentType;
        private String countryCode;
        private String from;
        private String content;
        private List<SmsSendDto> messages;
    }

    // 인증번호 전송 후 반환할 데이터
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SmsResponseDto {
        private String requestId;
        @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "Asia/Seoul")
        private LocalDateTime requestTime; // 요청 시간
        private String statusCode; // 응답 코드
        private String statusName; // 응답 내용
    }
}
