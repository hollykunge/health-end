package com.tosave.healthend.service;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class BaiduOcrService {
    public static final String APP_ID = "48680258";
    public static final String API_KEY = "UQ314sqFl0DeTe3u8oQZX0RS";
    public static final String SECRET_KEY = "KR8ZcugHVj8t2DGQC6G2YqtGU4KA6k12";
    private final RestTemplate restTemplate;

    // 构造函数
    public BaiduOcrService() {
        this.restTemplate = new RestTemplate();
    }

    public JSONObject getOcrAip(byte[] fileData) {
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用接口
        JSONObject res = client.meter(fileData, new HashMap<String, String>());
        System.out.println(res.toString(2));
        return res;
    }
}

