package com.ablez.admin.sms.service;

import com.ablez.admin.sms.dto.SmsDto.SmsRequestDto;
import com.ablez.admin.sms.dto.SmsDto.SmsResponseDto;
import com.ablez.admin.sms.dto.SmsDto.SmsSendDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
@Transactional
public class SmsService {
    @Value("${naver-cloud.accessKey}")
    private String accessKey;
    @Value("${naver-cloud.secretKey}")
    private String secretKey;
    @Value("${naver-cloud.serviceId}")
    private String serviceId;
    @Value("${naver-cloud.senderPhone}")
    private String fromPhone;
    @Value("${naver-cloud.algorithm}")
    private String algorithm;

    public SmsResponseDto sendMessage(String content, SmsSendDto phoneNumber)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException, URISyntaxException {
        // HTTP request header 설정
        HttpHeaders headers = setHeader();

        // 보낼 대상 선정
        List<SmsSendDto> smsSendDtos = new ArrayList<>();
        smsSendDtos.add(phoneNumber);

        // SMS 전송 관련 객체 생성(API 요청에 필요한 데이터 생성)
        SmsRequestDto requestDto = SmsRequestDto.builder()
                .type("SMS")
                .contentType("COMM")
                .countryCode("82")
                .from(fromPhone)
                .content(content)
                .messages(smsSendDtos)
                .build();

        // HTTP 요청 객체 생성
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(requestDto);
        HttpEntity<String> httpBody = new HttpEntity<>(body, headers);

        // RestTemplate 객체 생성
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        // 네이버 SMS API POST 요청
        return restTemplate.postForObject(
                new URI("https://sens.apigw.ntruss.com/sms/v2/services/" + serviceId + "/messages"),
                httpBody,
                SmsResponseDto.class
        );
    }

    private HttpHeaders setHeader() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        Long time = System.currentTimeMillis();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", accessKey);
        headers.set("x-ncp-apigw-signature-v2", makeSignature(time));

        return headers;
    }

    private String makeSignature(Long time)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String method = "POST";
        String url = "/sms/v2/services/" + serviceId + "/messages";
        String timestamp = time.toString();

        String message = new StringBuilder()
                .append(method)
                .append(" ")
                .append(url)
                .append('\n')
                .append(timestamp)
                .append('\n')
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), algorithm);
        Mac mac = Mac.getInstance(algorithm);
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodedBase64String = Base64.encodeBase64String(rawHmac);

        return encodedBase64String;
    }
}
