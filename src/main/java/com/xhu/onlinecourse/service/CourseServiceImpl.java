package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Course;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;
import com.xhu.onlinecourse.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public PageInfo<CourseTeacherVo> courseList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CourseTeacherVo> courseList = courseMapper.getCourseList();
        return new PageInfo<>(courseList);
    }

    @Override
    public CourseTeacherVo getCourseById(Long courseId) {
        return courseMapper.getCourseById(courseId);
    }

    @Override
    public PageInfo<CourseTeacherVo> getStudentCourseList(Long studentId,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CourseTeacherVo> studentCourseList = courseMapper.getStudentCourseList(studentId);
        return new PageInfo<>(studentCourseList);
    }
}
