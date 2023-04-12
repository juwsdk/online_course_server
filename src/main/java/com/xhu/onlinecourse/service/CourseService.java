package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.CourseRes;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;

import java.util.List;
import java.util.Map;

public interface CourseService {
    //获取课程列表，分页查询
    PageInfo<CourseTeacherVo> courseList(int pageNum, int pageSize);

    //通过课程id获取教师及课程相关信息
    CourseTeacherVo getCourseById(Long courseId);

    //获取学生选修的课程
    PageInfo<CourseTeacherVo> getStudentCourseList(Long studentId, int pageNum, int pageSize);

    //获取课程资源列表
    List<CourseRes> getCourseResList(Long courseId);

    //获取选课量前三的课程，用作走马灯
    List<CourseTeacherVo> getTopThreeCourseList();

    //发送学生选择的课程总数和选择的课程数
    Map<String, Object> stduentCourseCount(Long studentId);
}
