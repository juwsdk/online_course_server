package com.xhu.onlinecourse.controller;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.CourseRes;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;
import com.xhu.onlinecourse.service.CourseService;
import com.xhu.onlinecourse.utils.Result;
import com.xhu.onlinecourse.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "课程接口")
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    //课程选择页面发送的信息
    @ApiOperation(value = "查询所有课程")
    @RequestMapping(value = "/courseList", method = RequestMethod.GET)
    public Result<PageInfo<CourseTeacherVo>> courseList(@RequestParam(defaultValue = "1") int pageNum,
                                                        @RequestParam(defaultValue = "5") int pageSize) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), courseService.courseList(pageNum, pageSize));
    }

    //返回课程详情用作展示
    @ApiOperation("通过课程Id查询课程详情")
    @RequestMapping(value = "/courseSelectDetail", method = RequestMethod.GET)
    public Result<CourseTeacherVo> getCourseById(@RequestParam(name = "courseId") Long courseId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), courseService.getCourseById(courseId));
    }

    //学生已经选择了课程后的课程接口发送的信息
    @ApiOperation(value = "根据学号查询学生选择的课程")
    @RequestMapping(value = "/{studentId}/courseList", method = RequestMethod.GET)
    public Result<PageInfo<CourseTeacherVo>> stuCourseList(@PathVariable Long studentId,
                                                           @RequestParam(defaultValue = "1") int pageNum,
                                                           @RequestParam(defaultValue = "10") int pageSize) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), courseService.getStudentCourseList(studentId, pageNum, pageSize));
    }

    //查询学生是否选择了指定课程号的课程
    @ApiOperation(value = "查询学生是否选择了指定课程号的课程")
    @RequestMapping(method = RequestMethod.GET, value = "/courseChoose/{courseId}/{studentId}")
    public Result<Integer> studentIsChooseCourse(@PathVariable Long courseId,
                                                 @PathVariable Long studentId) {

        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), courseService.studentIsChooseCourse(courseId, studentId));
    }

    //学生选择课程
    @ApiOperation(value = "学生选择指定课程号的课程")
    @RequestMapping(method = RequestMethod.PUT, value = "/courseChoose/{courseId}/{studentId}")
    public Result<Integer> studentChooseCourse(@PathVariable Long courseId,
                                               @PathVariable Long studentId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), courseService.studentChooseCourse(courseId, studentId));
    }

    //发送学生网课视频
    @ApiOperation(value = "根据课程号查询课程资源")
    @RequestMapping(value = "/courseList/{courseId}", method = RequestMethod.POST)
    public Result<List<CourseRes>> stuCourseRouterList(@PathVariable Long courseId) {

        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), courseService.getCourseResList(courseId));
    }

    //返回前三的课程作为走马灯
    @ApiOperation(value = "查询学生选择最多的三门课")
    @RequestMapping(value = "/courseTopList", method = RequestMethod.GET)
    public Result<List<CourseTeacherVo>> getTopTreeCourseList() {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), courseService.getTopThreeCourseList());
    }

    @ApiOperation(value = "根据学生Id发送课程总数与学生选课数")
    @RequestMapping(value = "/{studentId}/countCourse", method = RequestMethod.GET)
    public Result<Map<String, Object>> studentCourseCount(@PathVariable Long studentId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), courseService.stduentCourseCount(studentId));
    }

}
