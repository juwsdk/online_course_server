package com.xhu.onlinecourse.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/*
工具类，处理文件的一些基本操作
 */

public class FileUtil {

    public static final String bathPath = System.getProperty("user.dir") + "/src/main/resources/static";

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