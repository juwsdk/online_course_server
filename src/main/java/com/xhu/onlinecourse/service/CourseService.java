package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Course;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;

public interface CourseService {
    PageInfo<CourseTeacherVo> courseList(int pageNum, int pageSize);
    CourseTeacherVo getCourseById(Long courseId);

    PageInfo<CourseTeacherVo> getStudentCourseList(Long studentId,int pageNum,int pageSize);
}
