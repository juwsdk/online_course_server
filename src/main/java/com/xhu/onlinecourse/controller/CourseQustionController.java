package com.xhu.onlinecourse.controller;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.CourseAttendance;
import com.xhu.onlinecourse.entity.CourseQuestions;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.service.CourseQuestionsService;
import com.xhu.onlinecourse.utils.Result;
import com.xhu.onlinecourse.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api("问题提出和回复")
@RestController
@RequestMapping("/courseQuestions")
public class CourseQustionController {
    @Autowired
    private CourseQuestionsService courseQuestionsService;

    @ApiOperation(value = "查询提出的问题")
    @RequestMapping(value = "/{courseId}/getAll", method = RequestMethod.POST)
    public Result<List<CourseQuestions>> getCourseQuestionsList(@PathVariable Long courseId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), courseQuestionsService.getCourseQuestionList(courseId));
    }

    @ApiOperation(value = "查询提出的问题的回答(针对问题的子回答)")
    @RequestMapping(value = "/{parentQuestionId}/getChild", method = RequestMethod.POST)
    public Result<List<CourseQuestions>> getCourseQuestionChildList(@PathVariable Long parentQuestionId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), courseQuestionsService.getCourseQuestionChildList(parentQuestionId));
    }

    @ApiOperation(value = "插入一条问题/针对问题回答问题")
    @RequestMapping(value = "/addQues", method = RequestMethod.PUT)
    public Result<Integer> addQuestion(@RequestBody CourseQuestions courseQuestions) {
//        courseQuestionsService.studentAskQuestion(courseQuestions)
//        System.out.println(courseQuestions);
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), courseQuestionsService.studentAskQuestion(courseQuestions));
    }

    @ApiOperation(value = "学生打卡")
    @RequestMapping(value = "/studentClock", method = RequestMethod.PUT)
    public Result<Integer> studentClockIn(@RequestBody CourseAttendance courseAttendance) {
        System.err.println(courseAttendance);
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), courseQuestionsService.studentClockIn(courseAttendance));
    }

    @ApiOperation(value = "教师查询所有学生打卡信息")
    @RequestMapping(value = "/{courseId}/studentClock")
    public Result<PageInfo<CourseAttendance>> allStudentIsClock(@PathVariable Long courseId,
                                                                @RequestParam(defaultValue = "1") int pageNum,
                                                                @RequestParam(defaultValue = "10") int pageSize,
                                                                @RequestParam(name = "fuzzyColumn", required = false) String columnName,
                                                                @RequestParam(name = "fuzzyValue", required = false) String value) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),
                courseQuestionsService.allStudentIsClock(courseId, pageNum, pageSize, columnName, value));
    }

}
