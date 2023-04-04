package com.xhu.onlinecourse.controller;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Course;
import com.xhu.onlinecourse.entity.CourseRes;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;
import com.xhu.onlinecourse.service.CourseService;
import com.xhu.onlinecourse.utils.Result;
import com.xhu.onlinecourse.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;
    //课程选择页面发送的信息
    @RequestMapping(value = "/courseList")
    public Result<PageInfo<CourseTeacherVo>> CourseList(@RequestParam(defaultValue = "1") int pageNum,
                                                        @RequestParam(defaultValue = "5") int pageSize){
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), courseService.courseList(pageNum,pageSize));
    }
    //返回课程详情用作展示
    @RequestMapping(value = "/courseSelectDetail")
    public Result<CourseTeacherVo> getCourseById(@RequestParam(name="courseId") Long courseId){
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), courseService.getCourseById(courseId));
    }

    //学生已经选择了课程后的课程接口发送的信息
    @RequestMapping(value = "/{studentId}/courseList")
    public Result<PageInfo<CourseTeacherVo>> stuCourseList(@PathVariable Long studentId,
                                            @RequestParam(defaultValue = "1") int pageNum,
                                            @RequestParam(defaultValue = "10") int pageSize){
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),courseService.getStudentCourseList(studentId,pageNum,pageSize));
    }
    //发送学生网课视频
    @RequestMapping(method = RequestMethod.POST,value ="/courseList/{courseId}" )
    public Result<List<CourseRes>> stuCourseRouterList(@PathVariable Long courseId){

        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),courseService.getCourseResList(courseId));
    }
    //返回前三的课程作为走马灯
    @RequestMapping(method = RequestMethod.GET,value = "/courseTopList")
    public Result<List<CourseTeacherVo>> getTopTreeCourseList(){
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), courseService.getTopThreeCourseList());
    }

}
