package com.xhu.onlinecourse.controller;

import com.xhu.onlinecourse.entity.CourseHomework;
import com.xhu.onlinecourse.service.HomeWorkService;
import com.xhu.onlinecourse.utils.FileTypeUtil;
import com.xhu.onlinecourse.utils.Result;
import com.xhu.onlinecourse.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Api(value = "课程作业接口")
@RestController
@RequestMapping("/homework")
public class CourseHomeWorkController {
    @Autowired
    private HomeWorkService homeWorkService;

    private static final String bathPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static";

    //发送给学生作业
    @ApiOperation("通过课程号发送给学生作业信息")
    @PostMapping("/{courseId}/getHomework")
    public Result<List<CourseHomework>> getHomework(@PathVariable Long courseId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), homeWorkService.homeworkListByCourseId(courseId));
    }

    //下载作业附件
    @ApiOperation(value = "下载作业附件")
    @PostMapping("/downLoad/{teacherId}/{courseId}/{courseHomeworkRes}")
    public ResponseEntity<byte[]> getHomeWorkRes(@PathVariable String teacherId,
                                                 @PathVariable String courseId,
                                                 @PathVariable String courseHomeworkRes) throws IOException {
        String path = String.format("%s/%s/%s/%s/%s/%s", bathPath, teacherId, courseId, "homework", "homeworkDistribute", courseHomeworkRes);
        //获得是什么类型的文件
        String fileType = FileTypeUtil.detectFileType(path);//得到了 .docx
        System.out.println(path);
        Path filePath = Paths.get(path);
        byte[] fileBytes = Files.readAllBytes(filePath);
        System.out.println(fileBytes.length);
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        //请求类型
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);//这两句一样
        // headers.setHeader("content-type", "application/octet-stream;charset=UTF-8");
        //发送的长度
        headers.setContentLength(fileBytes.length);
        //发送的文件类型
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + courseHomeworkRes);
        return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
    }
//    @ApiOperation(value = "教师上传视频")
    @PostMapping("/homeWorkUpload")
    public Result<String> homeWorkUpload(){
        String data="";
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),data );
    }

}
