package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Course;
import com.xhu.onlinecourse.entity.CourseRes;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;

import java.util.List;

public interface CourseService {
    PageInfo<CourseTeacherVo> courseList(int pageNum, int pageSize);
    CourseTeacherVo getCourseById(Long courseId);

    PageInfo<CourseTeacherVo> getStudentCourseList(Long studentId,int pageNum,int pageSize);
    List<CourseRes> getCourseResList(Long courseId);
    List<CourseTeacherVo> getTopThreeCourseList();
}
