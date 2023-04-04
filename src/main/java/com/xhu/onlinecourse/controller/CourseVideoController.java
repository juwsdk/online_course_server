package com.xhu.onlinecourse.controller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/video")
public class CourseVideoController {

    private static final String bathPath=System.getProperty("user.dir")+"\\src\\main\\resources\\static";



    @GetMapping("/getResource")
    public ResponseEntity<byte[]> getVideo(@RequestParam String srcUrl) throws IOException {
//        String decodedUrl = URLDecoder.decode(srcUrl, StandardCharsets.UTF_8.toString());
//        String fixedFilename = decodedUrl.replace("=", "");
        Path videoPath = Paths.get(bathPath+srcUrl);
        byte[] videoBytes = Files.readAllBytes(videoPath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(videoBytes.length);
        headers.set("Content-Disposition", "attachment; filename=\"video.mp4\"");
        return new ResponseEntity<>(videoBytes, headers, HttpStatus.OK);
    }
}
