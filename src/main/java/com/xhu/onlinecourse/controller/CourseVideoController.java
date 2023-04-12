package com.xhu.onlinecourse.controller;

import com.xhu.onlinecourse.entity.CourseRes;
import com.xhu.onlinecourse.entity.aboutfile.FileData;
import com.xhu.onlinecourse.service.TeacherService;
import com.xhu.onlinecourse.utils.FileUtil;
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

    private static final String bathPath = System.getProperty("user.dir") + "/src/main/resources/static";

    @ApiOperation(value = "获取指定的视频")
    @GetMapping("/getResource")
    public ResponseEntity<byte[]> getVideo(@RequestParam String srcUrl) throws IOException {
        return FileUtil.sendFileBytes(bathPath + srcUrl, "video.mp4");
    }

    @ApiOperation(value = "教师上传视频")//ModelAttribute可以接收前端formData后端用javabean接收
    @PostMapping("/upload")
    public Result<Integer> handleFileUpload(@ModelAttribute FileData fileData) throws IOException {
        //System.out.println(fileData.getFileRaw().getSize());
        //String name = fileData.getResFileName().substring(0, fileData.getResFileName().indexOf("."));
        //System.err.println(fileData);
        //String data = name + "已经上传成功!";
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherAddRes(fileData, bathPath));
    }

    @ApiOperation(value = "教师查看上传的资源列表")
    @PostMapping("/{courseId}/showFileList")
    public Result<List<CourseRes>> showFileList(@PathVariable Long courseId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherResById(courseId));
    }

    @ApiOperation(value = "教师查看修改资源")
    @PostMapping("/alterFile")
    public Result<Integer> fileAlter(CourseRes courseRes) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherResAlter(courseRes));
    }

    @ApiOperation(value = "教师删课程的一个资源")
    @PostMapping("/{courseResId}/fileDelete")
    public Result<Integer> fileDelete(@PathVariable Long courseResId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherDeleteRes(courseResId));
    }

    @ApiOperation(value = "教师删除这个课程的所有资源")
    @PostMapping("/{courseId}/filesDelete")
    public Result<Integer> fileListDelte(@PathVariable Long courseId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherDeleteAllRes(courseId));
    }


}
