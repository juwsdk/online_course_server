package com.xhu.onlinecourse.controller;

import com.xhu.onlinecourse.entity.CourseHomework;
import com.xhu.onlinecourse.entity.CourseHomeworkScore;
import com.xhu.onlinecourse.entity.CourseHomeworkSubmit;
import com.xhu.onlinecourse.entity.aboutfile.HomeworkFileData;
import com.xhu.onlinecourse.entity.aboutfile.HomeworkSubmitFileData;
import com.xhu.onlinecourse.entity.vo.CourseSubmitVo;
import com.xhu.onlinecourse.service.HomeWorkService;
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
import java.util.Map;

@Api(value = "课程作业接口")
@RestController
@RequestMapping("/homework")
public class CourseHomeWorkController {
    @Autowired
    private HomeWorkService homeWorkService;

    //发送给学生作业
    @ApiOperation("通过课程号发送给[学生/自行查看]作业信息")
    @PostMapping("/{courseId}/getHomework")
    public Result<List<CourseHomework>> getHomework(@PathVariable Long courseId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),
                homeWorkService.homeworkListByCourseId(courseId));
    }

    //下载作业附件
    @ApiOperation(value = "学生下载作业附件")
    @RequestMapping(value = "/downLoad", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getHomeworkRes(@RequestParam String teacherId,
                                                 @RequestParam String courseId,
                                                 @RequestParam String courseHomeworkRes) throws IOException {
        System.err.println(courseHomeworkRes);
        String path = String.format("%s/%s/%s/%s/%s/%s", FileUtil.bathPath, teacherId, courseId, "homework", "homeworkDistribute", courseHomeworkRes);
        System.out.println(path);
        return FileUtil.sendFileBytes(path, courseHomeworkRes);
    }

    //教师下载学生完成的作业附件
    @ApiOperation(value = "教师下载学生完成作业的附件")
    @RequestMapping(value = "/downLoadStuSubmit", method = RequestMethod.POST)
    public ResponseEntity<byte[]> getHomeworkSubmitRes(@RequestBody CourseSubmitVo courseSubmitVo) throws IOException {
        String fileName = homeWorkService.homeworkSubmitStudentOne(courseSubmitVo);
        String dirPath = String.format("%s/%s/%s/homework/homeworkSubmit/%s", FileUtil.bathPath,
                courseSubmitVo.getTeacherId(), courseSubmitVo.getCourseId(), fileName);
        return FileUtil.sendFileBytes(dirPath, fileName);
    }

    @ApiOperation(value = "教师上传作业")
    @RequestMapping(value = "/homeWorkUpload", method = RequestMethod.POST)
    public Result<Integer> homeWorkUpload(@ModelAttribute HomeworkFileData homeWorkFileData) throws IOException {

        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),
                homeWorkService.teacherUploadHomework(homeWorkFileData, FileUtil.bathPath));
    }

    @ApiOperation(value = "查询学生之前上传的作业")
    @RequestMapping(value = "/{studentId}/{courseHomeworkId}/homeWorkFinishedUpload", method = RequestMethod.POST)
    public Result<CourseHomeworkSubmit> homeWorkFinishedUploadBefo(@PathVariable Long studentId,
                                                                   @PathVariable Long courseHomeworkId) throws IOException {

        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),
                homeWorkService.studentSubmitHomeworkBefore(courseHomeworkId, studentId));
    }

    @ApiOperation(value = "学生修改上传的作业")
    @RequestMapping(value = "/homeWorkFinishedUpload", method = RequestMethod.PUT)
    public Result<Integer> updateHomeWorkFinishedUpload(@ModelAttribute HomeworkSubmitFileData homeworkSubmitFileData) throws IOException {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),
                homeWorkService.studentUpdateHomework(homeworkSubmitFileData, FileUtil.bathPath));
    }

    @ApiOperation(value = "学生上传完成的作业")
    @RequestMapping(value = "/homeWorkFinishedUpload", method = RequestMethod.POST)
    public Result<Integer> homeWorkFinishedUpload(@ModelAttribute HomeworkSubmitFileData homeWorkFileData) throws IOException {

        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),
                homeWorkService.studentSubmitHomework(homeWorkFileData, FileUtil.bathPath));
    }

    @ApiOperation(value = "完成作业的学生学号和姓名列表")
    @RequestMapping(value = "/{courseHomeworkId}/count", method = RequestMethod.POST)
    public Result<List<Map<String, String>>> studentCountFinish(@PathVariable Long courseHomeworkId) {

        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),
                homeWorkService.studentCountFinish(courseHomeworkId));
    }

    @ApiOperation(value = "教师下载学生提交的作业附件")
    @RequestMapping(value = "/downLoadStudentRes/{teacherId}/{courseId}/{courseHomeworkRes}", method = RequestMethod.POST)
    public ResponseEntity<byte[]> getStudentHomeWorkRes(@PathVariable String teacherId,
                                                        @PathVariable String courseId,
                                                        @PathVariable String courseHomeworkRes) throws IOException {
        String path = String.format("%s/%s/%s/%s/%s/%s", FileUtil.bathPath, teacherId, courseId, "homework", "homeworkSubmit", courseHomeworkRes);
        return FileUtil.sendFileBytes(path, courseHomeworkRes);
    }

    @ApiOperation(value = "展示学生上传的作业列表")
    @RequestMapping(value = "/{courseHomeworkId}/showHomework", method = RequestMethod.POST)
    public Result<List<Map<String, CourseHomeworkSubmit>>> homeworkSubmitByHomeworkId(@PathVariable Long courseHomeworkId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),
                homeWorkService.homeworkSubmitByHomeworkId(courseHomeworkId));
    }

    @ApiOperation(value = "没有完成作业的学生姓名")
    @RequestMapping(value = "/{courseHomeworkId}/showHomework", method = RequestMethod.GET)
    public Result<List<String>> studentCountUnFinish(@PathVariable Long courseHomeworkId) {

        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),
                homeWorkService.homeworkNotSubmitByHomeworkId(courseHomeworkId));
    }

    @ApiOperation(value = "教师给学生打分")
    @RequestMapping(value = "/scoreToStudent", method = RequestMethod.PUT)
    public Result<Integer> scoreToStudent(@RequestBody CourseHomeworkScore courseHomeworkScore) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),
                homeWorkService.scoreToStudent(courseHomeworkScore));
    }

    @ApiOperation(value = "学生查看得分")
    @RequestMapping(value = "/{studentId}/{courseHomeworkId}/scoreShowToStudent", method = RequestMethod.GET)
    public Result<Integer> scoreShowToStudent(@PathVariable Long studentId,
                                              @PathVariable Long courseHomeworkId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),
                homeWorkService.studentShowScore(courseHomeworkId, studentId));
    }

}
