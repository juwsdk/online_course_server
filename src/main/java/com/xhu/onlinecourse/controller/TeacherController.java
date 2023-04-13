package com.xhu.onlinecourse.controller;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.entity.Teacher;
import com.xhu.onlinecourse.entity.aboutfile.CourseFile;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;
import com.xhu.onlinecourse.entity.vo.StudentCourseVo;
import com.xhu.onlinecourse.service.TeacherService;
import com.xhu.onlinecourse.utils.Result;
import com.xhu.onlinecourse.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Api(value = "教师接口")
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    private static final String bathPath = System.getProperty("user.dir") + "/src/main/resources/static";

    @ApiOperation(value = "查询所有教师信息")
    @RequestMapping("/teacherList")
    public Result<PageInfo<Teacher>> teacherList(@RequestParam(defaultValue = "1") int pageNum,
                                                 @RequestParam(defaultValue = "10") int pageSize,
                                                 @RequestParam(name = "fuzzyColumn", required = false) String columnName,
                                                 @RequestParam(name = "fuzzyValue", required = false) String value) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherList(pageNum, pageSize, columnName, value));
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

    @ApiOperation(value = "通过课程id和教师id查询该教师教授的学生信息")
    @RequestMapping("/{teacherId}/{courseId}/studentList")
    public Result<PageInfo<Student>> studentListByTeacherCourseId(@RequestParam(defaultValue = "1") int pageNum,
                                                                  @RequestParam(defaultValue = "10") int pageSize,
                                                                  @PathVariable Long teacherId,
                                                                  @PathVariable Long courseId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.studentListByTeacherCourseId(teacherId, courseId, pageNum, pageSize));
    }

    @ApiOperation(value = "通过教师id找到教师教授的学生信息")
    @RequestMapping("/{teacherId}/studentList")
    public Result<PageInfo<StudentCourseVo>> studentCourseListByTeacherId(@RequestParam(defaultValue = "1") int pageNum,
                                                                          @RequestParam(defaultValue = "10") int pageSize,
                                                                          @PathVariable Long teacherId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.studentCourseListByTeacherId(pageNum, pageSize, teacherId));
    }

    @ApiOperation(value = "查询有多少学生选了这个教师的课程")
    @RequestMapping(path = "/{teacherId}/countStudent", method = RequestMethod.GET)
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
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.courseInsert(courseFile, bathPath));
    }

}
