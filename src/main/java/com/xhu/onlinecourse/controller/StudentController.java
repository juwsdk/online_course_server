package com.xhu.onlinecourse.controller;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.entity.vo.StudentAttendanceVo;
import com.xhu.onlinecourse.service.StudentService;
import com.xhu.onlinecourse.utils.Result;
import com.xhu.onlinecourse.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Api(value = "学生接口")
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "查询所有学生")
    @RequestMapping("/studentList")
    public Result<PageInfo<Student>> studentList(@RequestParam(defaultValue = "1") int pageNum,
                                                 @RequestParam(defaultValue = "10") int pageSize,
                                                 @RequestParam(name = "fuzzyColumn", required = false) String columnName,
                                                 @RequestParam(name = "fuzzyValue", required = false) String value) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),
                studentService.studentList(pageNum, pageSize, columnName, value));
    }

    @ApiOperation(value = "根据学生id查询学生")
    @RequestMapping(value = "/{studentId}", method = RequestMethod.POST)
    public Result<Student> studentById(@PathVariable Long studentId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), studentService.studentById(studentId));
    }

    @ApiOperation(value = "添加学生")
    @RequestMapping(value = "/studentInsert", method = RequestMethod.POST)
    public Result<Integer> studentInsert(@RequestBody Student student) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), studentService.studentInsert(student));
    }

    @ApiOperation(value = "修改学生")
    @RequestMapping(value = "/studentUpdate", method = RequestMethod.PUT)
    public Result<Integer> studentUpdate(@RequestBody Student student) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), studentService.studentUpdate(student));
    }

    @ApiOperation(value = "删除学生")
    @RequestMapping(value = "/studentDelete", method = RequestMethod.DELETE)
    public Result<Integer> studentDelete(@RequestBody Student student) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), studentService.studentDelete(student));
    }


    @ApiOperation(value = "统计学生作业完成情况的饼图请求的数据")
    @RequestMapping(value = "/{studentId}/studentHomeworkCount", method = RequestMethod.GET)
    public Result<Map<String, Integer>> studentHomeworkCount(@PathVariable Long studentId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), studentService.studentHomeWork(studentId));
    }

    @ApiOperation(value = "统计学生提问情况饼图的请求数据")//学生提出的问题和总的问题数
    @RequestMapping(value = "/{studentId}/studentQuestionsNum", method = RequestMethod.GET)
    public Result<Map<String, Integer>> studentQuestionsNum(@PathVariable Long studentId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), studentService.studentQuestionsNum(studentId));
    }

    @ApiOperation(value = "学生最近三周的打卡情况折线图")
    @RequestMapping(value = "/{studentId}/studentAttendance", method = RequestMethod.GET)
    public Result<StudentAttendanceVo> studentAttendance(@PathVariable Long studentId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), studentService.studentAttendance(studentId));
    }

}
