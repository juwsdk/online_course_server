package com.xhu.onlinecourse.controller;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.entity.Teacher;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;
import com.xhu.onlinecourse.entity.vo.StudentCourseVo;
import com.xhu.onlinecourse.service.TeacherService;
import com.xhu.onlinecourse.utils.Result;
import com.xhu.onlinecourse.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "教师接口")
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "查询所有教师信息")
    @RequestMapping("/teacherList")
    public Result<PageInfo<Teacher>> teacherList(@RequestParam(defaultValue = "1") int pageNum,
                                                 @RequestParam(defaultValue = "10") int pageSize) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherList(pageNum, pageSize));
    }
    @ApiOperation(value = "通过教师Id查询教师信息")
    @RequestMapping("/{teacherId}/teacherList")
    public Result<PageInfo<CourseTeacherVo>> teacherListById(@RequestParam(defaultValue = "1") int pageNum,
                                                             @RequestParam(defaultValue = "8") int pageSize,
                                                             @PathVariable Long teacherId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), teacherService.teacherListById(pageNum, pageSize, teacherId));
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
                                                                          @PathVariable Long teacherId){
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),teacherService.studentCourseListByTeacherId(pageNum,pageSize,teacherId));
    }
}
