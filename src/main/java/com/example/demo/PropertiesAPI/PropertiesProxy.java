package com.example.demo.PropertiesAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PropertiesProxy {
    @Value("${properties.API_KEY}")
    private String API_KEY;

    @Value("${properties.BASE_URL}")
    private String BASE_URL;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api/data")
    public ResponseEntity<Map<String, Object>> getData(@RequestBody RequestParams params) {
        Map<String, Object> jsonResponse = new HashMap<>();
        try {
            StringBuilder urlBuilder = new StringBuilder(BASE_URL);
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + API_KEY);
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(params.getPageNo(), "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(params.getNumOfRows(), "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(params.getLawdCd(), "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(params.getDealYmd(), "UTF-8"));
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
            }

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            // XML 응답을 JSON으로 변환
//            jsonResponse.put("response", sb.toString());

            // XML 응답을 JSON으로 변환
            XmlMapper xmlMapper = new XmlMapper();
            JsonNode jsonNode = xmlMapper.readTree(sb.toString().getBytes());
            jsonResponse.put("response", jsonNode);


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Failed to fetch data"));
        }

        return ResponseEntity.ok(jsonResponse);
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
