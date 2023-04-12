package com.xhu.onlinecourse.controller;

import com.xhu.onlinecourse.entity.CourseHomework;
import com.xhu.onlinecourse.entity.aboutfile.HomeworkFileData;
import com.xhu.onlinecourse.entity.aboutfile.HomeworkSubmitFileData;
import com.xhu.onlinecourse.service.HomeWorkService;
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Api(value = "课程作业接口")
@RestController
@RequestMapping("/homework")
public class CourseHomeWorkController {
    @Autowired
    private HomeWorkService homeWorkService;

    private static final String bathPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static";

    //发送给学生作业
    @ApiOperation("通过课程号发送给[学生/自行查看]作业信息")
    @PostMapping("/{courseId}/getHomework")
    public Result<List<CourseHomework>> getHomework(@PathVariable Long courseId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), homeWorkService.homeworkListByCourseId(courseId));
    }

    //下载作业附件
    @ApiOperation(value = "学生下载作业附件")
    @PostMapping("/downLoad/{teacherId}/{courseId}/{courseHomeworkRes}")
    public ResponseEntity<byte[]> getHomeWorkRes(@PathVariable String teacherId,
                                                 @PathVariable String courseId,
                                                 @PathVariable String courseHomeworkRes) throws IOException {
        String path = String.format("%s/%s/%s/%s/%s/%s", bathPath, teacherId, courseId, "homework", "homeworkDistribute", courseHomeworkRes);
        return FileUtil.sendFileBytes(path, courseHomeworkRes);
    }

    @ApiOperation(value = "教师上传作业")
    @PostMapping("/homeWorkUpload")
    public Result<Integer> homeWorkUpload(@ModelAttribute HomeworkFileData homeWorkFileData) throws IOException {

        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), homeWorkService.teacherUploadHomework(homeWorkFileData, bathPath));
    }

    @ApiOperation(value = "学生上传完成的作业")
    @PostMapping("/homeWorkFinishedUpload")
    public Result<Integer> homeWorkFinishedUpload(@ModelAttribute HomeworkSubmitFileData homeWorkFileData) throws IOException {

        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), homeWorkService.studentSubmitHomework(homeWorkFileData, bathPath));
    }

    @ApiOperation(value = "完成作业的学生学号和姓名列表")
    @PostMapping("/{courseHomeworkId}/count")
    public Result<List<Map<String, String>>> studentCountFinish(@PathVariable Long courseHomeworkId) {

        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), homeWorkService.studentCountFinish(courseHomeworkId));
    }

    @ApiOperation(value = "教师下载学生提交的作业附件")
    @PostMapping("/downLoadStudentRes/{teacherId}/{courseId}/{courseHomeworkRes}")
    public ResponseEntity<byte[]> getStudentHomeWorkRes(@PathVariable String teacherId,
                                                        @PathVariable String courseId,
                                                        @PathVariable String courseHomeworkRes) throws IOException {
        String path = String.format("%s/%s/%s/%s/%s/%s", bathPath, teacherId, courseId, "homework", "homeworkSubmit", courseHomeworkRes);
        return FileUtil.sendFileBytes(path, courseHomeworkRes);
    }

}
