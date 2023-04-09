package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.CourseRes;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;

import java.util.List;
import java.util.Map;

public interface CourseService {
    PageInfo<CourseTeacherVo> courseList(int pageNum, int pageSize);
    CourseTeacherVo getCourseById(Long courseId);

    PageInfo<CourseTeacherVo> getStudentCourseList(Long studentId,int pageNum,int pageSize);
    List<CourseRes> getCourseResList(Long courseId);

    List<CourseTeacherVo> getTopThreeCourseList();

    Map<String, Object> stduentCourseCount(Long studentId);//发送学生选择的课程总数和选择的课程数
}
