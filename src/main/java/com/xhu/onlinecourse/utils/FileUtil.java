package com.xhu.onlinecourse.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
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

    //递归删除文件
    public static Boolean deleteAllFiles(File dir) {
        //文件夹不存在
        if (!dir.exists())
            return true;
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory())
                    deleteAllFiles(file);
                else
                    file.delete();
            }
        }
        return dir.delete();
    }

    //发送给前端文件
    public static ResponseEntity<byte[]> sendFileBytes(String path, String fileName/*包含后缀名*/) throws IOException {
        Path filePath = Paths.get(path);
        byte[] fileBytes = Files.readAllBytes(filePath);
        System.out.println(fileBytes.length);
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        //请求类型
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //发送的长度
        headers.setContentLength(fileBytes.length);
        //发送的文件类型
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
    }
}