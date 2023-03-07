package com.xhu.onlinecourse.controller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/video")
public class CourseVideoController {

    private static final String VIDEO_PATH = "C:\\Users\\wp125\\Desktop\\学习资料\\数学\\复变函数与积分变换 60集全 李红 华中科技大学\\保形映射\\1.mp4";
    private static final int CHUNK_SIZE = 4*1024 * 1024; // 1MB
    @GetMapping("/chunk")
    public ResponseEntity<byte[]> getVideo() throws IOException {
        Path videoPath = Paths.get(VIDEO_PATH);
        byte[] videoBytes = Files.readAllBytes(videoPath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(videoBytes.length);
        headers.set("Content-Disposition", "attachment; filename=\"video.mp4\"");
        return new ResponseEntity<>(videoBytes, headers, HttpStatus.OK);
    }
}
