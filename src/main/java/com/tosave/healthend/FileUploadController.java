package com.tosave.healthend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@RestController
public class FileUploadController {

    private static final String TEMP_DIR = "/tmp/uploads/"; // 定义文件保存的临时目录

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam HashMap<String, String> options) {
        try {
            // 确保临时目录存在
            File directory = new File(TEMP_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 将文件保存到临时目录
            String filename = file.getOriginalFilename();
            Path filepath = Paths.get(TEMP_DIR, filename);
            Files.write(filepath, file.getBytes());

            // 调用 meter 函数，传入文件路径和选项参数
            JSONObject result = meter(filepath.toString(), options);
            return ResponseEntity.ok(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("文件上传或处理失败");
        }
    }

    private JSONObject meter(String image, HashMap<String, String> options) {
        // 这里应该是您提供的 meter 函数的实现
        // 返回处理结果
    }
}

