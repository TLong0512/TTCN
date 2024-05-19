package com.team12.fantafilm.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class UploadImage {
    private final String path = "D:\\WORK_SPACE\\Java_Projects\\TTCN\\src\\main\\resources\\static\\admin\\assets\\images\\products";
    public File handerUpLoadFile(MultipartFile uploadFile) {
        String folderPath = path;
        File myUpLoadFolder = new File(folderPath);
        // thiếu thì tạo
        if (!myUpLoadFolder.exists()) {
            myUpLoadFolder.mkdirs();
        }
        File saveFile = null;
        try {
            // Lưu file vào thư mực
            saveFile = new File(myUpLoadFolder,uploadFile.getOriginalFilename());
            // chuyển dữ liệu sang file vừa tạo
            uploadFile.transferTo(saveFile);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return saveFile;
    }
}
