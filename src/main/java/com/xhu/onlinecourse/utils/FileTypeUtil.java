package com.xhu.onlinecourse.utils;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTypeUtil {
    //获取文件类型
    public static String detectFileType(String filePath) {
        try {
            Path path = Paths.get(filePath);
            String contentType = Files.probeContentType(path);
            if (contentType == null) {
                URL url = new URL("file://" + filePath);
                URLConnection connection = url.openConnection();
                contentType = connection.getContentType();
            }
            return contentType;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
