package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.CourseRes;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;
import com.xhu.onlinecourse.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<CourseRes> getCourseResList(Long courseId) {
        return courseMapper.getCourseResList(courseId);
    }

    @Override
    public List<CourseTeacherVo> getTopThreeCourseList() {
        return courseMapper.getTopThreeCourseList();
    }

    @Override
    public Map<String, Object> stduentCourseCount(Long studentId) {
        Map<String, Object> map = new HashMap<>();
        map.put("countAll", courseMapper.countCourses());
        map.put("studentCountAll", courseMapper.countStudentCourses(studentId));
        return map;
    }
}
