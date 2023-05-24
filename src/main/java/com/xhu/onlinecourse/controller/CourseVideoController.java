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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Api(value = "视频资源接口")
@RestController
@RequestMapping("/video")
public class CourseVideoController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "获取指定的视频")
    @RequestMapping(value = "/getResource", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getVideo(@RequestParam String srcUrl) throws IOException {
        return FileUtil.sendFileBytes(FileUtil.bathPath + srcUrl, "video.mp4");
    }

    @ApiOperation(value = "教师上传视频")//ModelAttribute可以接收前端formData后端用javabean接收
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result<Integer> handleFileUpload(@ModelAttribute FileData fileData) throws IOException {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherAddRes(fileData, FileUtil.bathPath));
    }

    @ApiOperation(value = "教师查看上传的资源列表")
    @RequestMapping(value = "/{courseId}/showFileList", method = RequestMethod.POST)
    public Result<List<CourseRes>> showFileList(@PathVariable Long courseId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherResById(courseId));
    }

    @ApiOperation(value = "教师修改一个课程资源")
    @RequestMapping(value = "/fileUpdate", method = RequestMethod.PUT)
    public Result<Integer> fileAlter(@RequestBody CourseRes courseRes) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherResAlter(courseRes));
    }

    @ApiOperation(value = "教师删课程的一个资源")
    @RequestMapping(value = "/{teacherId}/fileDelete", method = RequestMethod.DELETE)
    public Result<Integer> fileDelete(@PathVariable Long teacherId,
                                      @RequestBody CourseRes courseRes) {
        System.err.println(courseRes);
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherDeleteRes(courseRes, teacherId, FileUtil.bathPath));
    }

    @ApiOperation(value = "教师删除这个课程的所有资源")
    @RequestMapping(value = "/{teacherId}/{courseId}/filesDelete", method = RequestMethod.DELETE)
    public Result<Integer> fileListDelete(@PathVariable Long teacherId,
                                          @PathVariable Long courseId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherDeleteAllRes(courseId, teacherId, FileUtil.bathPath));
    }


}
