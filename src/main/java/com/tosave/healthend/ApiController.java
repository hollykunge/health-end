package com.tosave.healthend;

import com.tosave.health.service.DataService;
import com.tosave.health.service.OcrService;
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
    private final OcrService ocrService;

    @Autowired
    public ApiController(DataService dataService, OcrService ocrService) {
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

    @GetMapping("/getAccessToken")
    public ApiResponse<String> getAccessToken() throws IOException {
        String accessToken = ocrService.getAccessToken();
        return ApiResponse.ofSuccess(accessToken);
    }

    @PostMapping("/upload")
    public ApiResponse<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            byte[] fileData = file.getBytes();
            // 这里你可以将fileData传递给服务层进行进一步处理
            // 例如保存到数据库、文件系统等
            ocrService.getOcrAip(fileData);
            return ApiResponse.ofSuccess("文件上传成功，大小: " + fileData.length + " 字节");
        } catch (IOException e) {
            return ApiResponse.ofFailure(500, "文件读取失败");
        }
    }
}