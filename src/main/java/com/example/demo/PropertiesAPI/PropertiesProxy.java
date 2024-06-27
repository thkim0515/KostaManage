package com.example.demo.PropertiesAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class PropertiesProxy {
    private final String API_KEY = "fnHXpQdXcUKuxNNB4JhjrTvdJrhjQ4gRUNzOLG9gwV4FeAFxzoE4A0TkbnDp%2FjptXWsbP4tx7gdqT%2B6nVcKWKQ%3D%3D";
    private final String BASE_URL = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev?_wadl&type=xml";

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api/data")
    public ResponseEntity<Map<String, Object>> getData(@RequestBody RequestParams params) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s?serviceKey=%s&pageNo=%s&numOfRows=%s&LAWD_CD=%s&DEAL_YMD=%s",
                BASE_URL, API_KEY, params.getPageNo(), params.getNumOfRows(), params.getLawdCd(), params.getDealYmd());


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        // XML 응답을 JSON으로 변환
        XmlMapper xmlMapper = new XmlMapper();
        Map<String, Object> jsonResponse = null;
        try {
            jsonResponse = xmlMapper.readValue(response.getBody(), Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Failed to parse XML"));
        }

        return ResponseEntity.ok(jsonResponse);
//        // 응답 헤더에 UTF-8 인코딩 설정
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
//        responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
//
//        return ResponseEntity.ok()
//                .headers(responseHeaders)
//                .body(response.getBody());
    }
}

class RequestParams {
    private String pageNo;
    private String numOfRows;
    private String lawdCd;
    private String dealYmd;

    // Getters and Setters
    public String getPageNo() { return pageNo; }
    public void setPageNo(String pageNo) { this.pageNo = pageNo; }

    public String getNumOfRows() { return numOfRows; }
    public void setNumOfRows(String numOfRows) { this.numOfRows = numOfRows; }

    public String getLawdCd() { return lawdCd; }
    public void setLawdCd(String lawdCd) { this.lawdCd = lawdCd; }

    public String getDealYmd() { return dealYmd; }
    public void setDealYmd(String dealYmd) { this.dealYmd = dealYmd; }
}
