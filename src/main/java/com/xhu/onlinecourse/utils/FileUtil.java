package com.xhu.onlinecourse.utils;

import org.springframework.http.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
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
        //跨域需要设置这一项
        headers.add("Access-Control-Expose-Headers ", "Content-Disposition");
        // 设置 Content-Disposition 头字段
        String encodeFileName = URLEncoder.encode(fileName, "utf-8");
        headers.add("Content-Disposition", "attachment;filename*=UTF-8''" + encodeFileName);
        return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
    }
}