package com.tosave.healthend.controller;

import com.tosave.healthend.entity.ApiResponse;
import com.tosave.healthend.service.BaiduOcrService;
import com.tosave.healthend.service.DataService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
class ApiController {

    private final DataService dataService;
    private final BaiduOcrService ocrService;

    @Autowired
    public ApiController(DataService dataService, BaiduOcrService ocrService) {
        this.dataService = dataService;
        this.ocrService = ocrService;
    }

    private final List<String> dataList = new ArrayList<>();

    @GetMapping("/data")
    public ApiResponse<List<String>> getDataList() {
        return ApiResponse.ofSuccess(dataList);
    }

    @PostMapping("/data")
    public ApiResponse<Void> addDataItem(@RequestBody String dataItem) {
        dataService.addDataItem(dataItem);
        return ApiResponse.ofSuccess(null);
    }

    @PostMapping("/ocr")
    public ApiResponse<String> getOcrContent(@RequestBody byte[] image) {
        // 此处应调用OCR服务进行识别，以下为模拟实现
        String ocrResult = "模拟OCR识别结果";
        return ApiResponse.ofSuccess(ocrResult);
    }

    @PostMapping("/upload")
    public ApiResponse<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            byte[] fileData = file.getBytes();
            JSONObject ocrResponse = ocrService.getOcrAip(fileData);

            JSONArray wordsResult = ocrResponse.getJSONArray("words_result");
            if (wordsResult != null && wordsResult.length() > 0) {
                StringBuilder ocrResultsBuilder = new StringBuilder();
                for (int i = 0; i < wordsResult.length(); i++) {
                    JSONObject word = wordsResult.getJSONObject(i);
                    ocrResultsBuilder.append(word.getString("words")).append(", ");
                }
                // 移除最后的逗号和空格
                String ocrResults = ocrResultsBuilder.toString().replaceAll(", $", "");
                return ApiResponse.ofSuccess("文件上传成功，OCR结果: " + ocrResults);
            } else {
                return ApiResponse.ofFailure(204, "没有识别到文本");
            }
        } catch (IOException e) {
            return ApiResponse.ofFailure(500, "文件读取失败: " + e.getMessage());
        } catch (JSONException e) {
            return ApiResponse.ofFailure(500, "JSON解析错误: " + e.getMessage());
        }
    }

}