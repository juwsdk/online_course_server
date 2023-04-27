package com.xhu.onlinecourse.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.CourseHomeworkScore;
import com.xhu.onlinecourse.entity.CourseRes;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;
import com.xhu.onlinecourse.mapper.CourseMapper;
import com.xhu.onlinecourse.service.CourseService;
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

    //获取课程列表，分页查询
    @Override
    public PageInfo<CourseTeacherVo> courseList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CourseTeacherVo> courseList = courseMapper.getCourseList();
        return new PageInfo<>(courseList);
    }

    //通过课程id获取教师及课程相关信息
    @Override
    public CourseTeacherVo getCourseById(Long courseId) {
        return courseMapper.getCourseById(courseId);
    }

    //获取学生选修的课程
    @Override
    public PageInfo<CourseTeacherVo> getStudentCourseList(Long studentId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CourseTeacherVo> studentCourseList = courseMapper.getStudentCourseList(studentId);
        return new PageInfo<>(studentCourseList);
    }

    //获取课程资源列表
    @Override
    public List<CourseRes> getCourseResList(Long courseId) {
        return courseMapper.getCourseResList(courseId);
    }

    //获取选课量前三的课程，用作走马灯
    @Override
    public List<CourseTeacherVo> getTopThreeCourseList() {
        return courseMapper.getTopThreeCourseList();
    }

    //发送学生选择的课程总数和选择的课程数
    @Override
    public Map<String, Object> stduentCourseCount(Long studentId) {
        Map<String, Object> map = new HashMap<>();
        map.put("countAll", courseMapper.countCourses());
        map.put("studentCountAll", courseMapper.countStudentCourses(studentId));
        return map;
    }

    //学生选择课程
    @Override
    public Integer studentChooseCourse(Long courseId, Long studentId) {
        return courseMapper.studentChooseCourse(courseId, studentId);
    }

    //学生是否选择了课程
    @Override
    public Integer studentIsChooseCourse(Long courseId, Long studentId) {
        if (courseMapper.studentIsChooseCourse(courseId, studentId) != null)
            return 1;
        return 0;
    }
}
