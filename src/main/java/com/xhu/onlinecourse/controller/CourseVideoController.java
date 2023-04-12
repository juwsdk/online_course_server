package com.xhu.onlinecourse.controller;

import com.xhu.onlinecourse.entity.CourseRes;
import com.xhu.onlinecourse.entity.aboutfile.FileData;
import com.xhu.onlinecourse.service.TeacherService;
import com.xhu.onlinecourse.utils.Result;
import com.xhu.onlinecourse.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Api(value = "视频资源接口")
@RestController
@RequestMapping("/video")
public class CourseVideoController {
    @Autowired
    TeacherService teacherService;

    private static final String bathPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static";

    @ApiOperation(value = "获取指定的视频")
    @GetMapping("/getResource")
    public ResponseEntity<byte[]> getVideo(@RequestParam String srcUrl) throws IOException {
//        String decodedUrl = URLDecoder.decode(srcUrl, StandardCharsets.UTF_8.toString());
//        String fixedFilename = decodedUrl.replace("=", "");
        Path videoPath = Paths.get(bathPath + srcUrl);
        byte[] videoBytes = Files.readAllBytes(videoPath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(videoBytes.length);
        headers.set("Content-Disposition", "attachment; filename=\"video.mp4\"");
        return new ResponseEntity<>(videoBytes, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "教师上传视频")
    @PostMapping("/{courseId}/upload")
    public Result<String> handleFileUpload(@PathVariable Long courseId,
                                           @ModelAttribute FileData fileData//ModelAttribute可以接收前端formData后端用javabean接收
    ) {
//        System.out.println(fileData.getFileRaw().getSize());
        String name = fileData.getResFileName().substring(0, fileData.getResFileName().indexOf("."));
//        System.err.println(fileData);
        String data = name + "已经上传成功!";
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    @ApiOperation(value = "教师查看上传的资源列表")
    @PostMapping("/{courseId}/showFileList")
    public Result<List<CourseRes>> showFileList(@PathVariable Long courseId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherResById(courseId));
    }


}
