package com.xhu.onlinecourse.controller;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Course;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.entity.Teacher;
import com.xhu.onlinecourse.entity.aboutfile.CourseFile;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;
import com.xhu.onlinecourse.entity.vo.StudentCourseVo;
import com.xhu.onlinecourse.service.TeacherService;
import com.xhu.onlinecourse.utils.FileUtil;
import com.xhu.onlinecourse.utils.Result;
import com.xhu.onlinecourse.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Api(value = "教师接口")
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;


    @ApiOperation(value = "查询所有教师信息")
    @RequestMapping(value = "/teacherList", method = RequestMethod.GET)
    public Result<PageInfo<Teacher>> teacherList(@RequestParam(defaultValue = "1") int pageNum,
                                                 @RequestParam(defaultValue = "10") int pageSize,
                                                 @RequestParam(name = "fuzzyColumn", required = false) String columnName,
                                                 @RequestParam(name = "fuzzyValue", required = false) String value) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherList(pageNum, pageSize, columnName, value));
    }

    @ApiOperation(value = "删除教师")
    @RequestMapping(value = "/teacherDelete", method = RequestMethod.POST)
    public Result<Integer> teacherDelete(@RequestBody Teacher teacher) {
//        System.err.println(teacher);
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherDelete(teacher));
    }

    @ApiOperation(value = "修改教师")
    @RequestMapping(value = "/teacherUpdate", method = RequestMethod.PUT)
    public Result<Integer> teacherUpdate(@RequestBody Teacher teacher) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherUpdate(teacher));
    }

    @ApiOperation(value = "增加教师")
    @RequestMapping(value = "/teacherInsert", method = RequestMethod.POST)
    public Result<Integer> teacherInsert(@RequestBody Teacher teacher) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherInsert(teacher));
    }

    @ApiOperation(value = "根据教师Id查询教师信息")
    @RequestMapping(path = "/{teacherId}/teacherOne", method = RequestMethod.POST)
    public Result<Teacher> teacherOne(@PathVariable Long teacherId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherOne(teacherId));
    }


    @ApiOperation(value = "通过教师Id查询教师教授的所有课程,分页查询")
    @RequestMapping(value = "/{teacherId}/teacherList", method = RequestMethod.GET)
    public Result<PageInfo<CourseTeacherVo>> teacherListById(@RequestParam(defaultValue = "1") int pageNum,
                                                             @RequestParam(defaultValue = "8") int pageSize,
                                                             @PathVariable Long teacherId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherListById(pageNum, pageSize, teacherId));
    }

    @ApiOperation(value = "通过教师Id查询教师教授的所有课程,非分页查询")
    @RequestMapping(value = "/{teacherId}/teacherList", method = RequestMethod.POST)
    public Result<List<CourseTeacherVo>> teacherListById(@PathVariable Long teacherId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherListById(teacherId));
    }



    @ApiOperation("教师删除自己课程的学生")
    @RequestMapping(value = "/{courseId}/studentRemove", method = RequestMethod.POST)
    public Result<Integer> studentRemove(@PathVariable Long courseId,
                                         @RequestBody StudentCourseVo studentCourseVo){
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),teacherService.studentRemove(courseId,studentCourseVo));
    }

    @ApiOperation(value = "通过课程id和教师id查询该教师教授的学生信息")
    @RequestMapping(value = "/{courseId}/{teacherId}/studentList", method = RequestMethod.GET)
    public Result<PageInfo<Student>> studentListByTeacherCourseId(@RequestParam(defaultValue = "1") int pageNum,
                                                                  @RequestParam(defaultValue = "10") int pageSize,
                                                                  @PathVariable Long teacherId,
                                                                  @PathVariable Long courseId,
                                                                  @RequestParam(name = "fuzzyColumn", required = false) String columnName,
                                                                  @RequestParam(name = "fuzzyValue", required = false) String value) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.studentListByTeacherCourseId(pageNum, pageSize, columnName, value, teacherId, courseId));
    }

    @ApiOperation(value = "通过教师id找到教师教授的学生信息")
    @RequestMapping(value = "/{teacherId}/studentList", method = RequestMethod.GET)
    public Result<PageInfo<StudentCourseVo>> studentCourseListByTeacherId(@RequestParam(defaultValue = "1") int pageNum,
                                                                          @RequestParam(defaultValue = "10") int pageSize,
                                                                          @PathVariable Long teacherId,
                                                                          @RequestParam(name = "fuzzyColumn", required = false) String columnName,
                                                                          @RequestParam(name = "fuzzyValue", required = false) String value) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.studentCourseListByTeacherId(pageNum, pageSize, columnName, value, teacherId));
    }



    @ApiOperation(value = "查询有多少学生选了这个教师的课程")
    @RequestMapping(value = "/{teacherId}/countStudent", method = RequestMethod.GET)
    public Result<Integer> teacherCourseStudentSelectNum(@PathVariable Long teacherId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherCourseStudentSelectNum(teacherId));
    }

    @ApiOperation(value = "查询教师教授的各个课程的选课人数")
    @RequestMapping(path = "/{teacherId}/countCourseStudent", method = RequestMethod.POST)
    public Result<List<Map<String, Integer>>> teacherTeachStudentCourseCount(@PathVariable Long teacherId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherTeachStudentCourseCount(teacherId));
    }

    @ApiOperation(value = "教师新增一门课程")
    @RequestMapping(path = "/courseInsert", method = RequestMethod.POST)
    public Result<Integer> teacherAddNewCourse(@ModelAttribute CourseFile courseFile) throws IOException {
        System.err.println(courseFile);
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.courseInsert(courseFile, FileUtil.bathPath));
    }

    @ApiOperation(value = "教师修改课程")
    @RequestMapping(path = "/courseUpdate", method = RequestMethod.POST)
    public Result<Integer> teacherUpdateCourse(@ModelAttribute CourseFile courseFile) throws IOException {
        System.err.println(courseFile);
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.courseUpdate(courseFile, FileUtil.bathPath));
    }

    @ApiOperation(value = "教师删除课程")
    @RequestMapping(path = "/{courseId}/courseDelete", method = RequestMethod.DELETE)
    public Result<Integer> teacherDeleteCourse(@PathVariable Long courseId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.courseDelete(courseId));
    }

    @ApiOperation(value = "教师教授的课程信息")
    @RequestMapping(path = "/{courseId}/courseShow", method = RequestMethod.GET)
    public Result<Course> getCourseInfoById(@PathVariable Long courseId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.getCourseInfoById(courseId));
    }

    @ApiOperation(value = "教师教授的课程信息中的图片")
    @RequestMapping(path = "/courseShow", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getCourseInfoById(@PathParam("teacherId") Long teacherId,
                                                    @PathParam("courseId") Long courseId,
                                                    @PathParam("courseImage") String courseImage) throws IOException {
        System.err.println(teacherId);
        String targetPath=String.format("%s/%s/%s/%s",FileUtil.bathPath,teacherId,courseId,courseImage);
        return FileUtil.sendFileBytes(targetPath,courseImage);
    }

}
