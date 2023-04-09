package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.CourseHomework;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.entity.Teacher;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;
import com.xhu.onlinecourse.entity.vo.StudentCourseVo;
import com.xhu.onlinecourse.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public PageInfo<Teacher> teacherList(int pageNum, int pageSize, String columnName, String value) {
        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> teacherList = teacherMapper.teacherList(columnName, value);
        return new PageInfo<>(teacherList);
    }

    @Override
    public PageInfo<CourseTeacherVo> teacherListById(int pageNum, int pageSize, Long teacherId) {
        PageHelper.startPage(pageNum, pageSize);
        List<CourseTeacherVo> courseTeacherVos = teacherMapper.teacherListById(teacherId);
        return new PageInfo<>(courseTeacherVos);
    }

    @Override
    public PageInfo<Student> studentListByTeacherCourseId(Long teacherId, Long courseId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> students = teacherMapper.studentListByTeacherCourseId(teacherId, courseId);
        return new PageInfo<>(students);
    }

    @Override
    public Boolean addHomeWork(CourseHomework courseHomework) {
        if (teacherMapper.addHomeWork(courseHomework) == 1)
            return true;
        return false;
    }

    @Override
    public List<CourseTeacherVo> teacherListById(Long teacherId) {
        return teacherMapper.teacherListById(teacherId);
    }

    @Override
    public int teacherCourseStudentSelectNum(Long teacherId) {
        return teacherMapper.teacherCourseStudentSelectNum(teacherId);
    }

    @Override
    public Teacher teacherOne(Long teacherId) {
        return teacherMapper.teacherOne(teacherId);
    }

    @Override
    public List<Map<String, Integer>> teacherTeachStudentCourseCount(Long teacherId) {
        return teacherMapper.teacherTeachStudentCourseCount(teacherId);
    }

    @Override
    public PageInfo<StudentCourseVo> studentCourseListByTeacherId(int pageNum, int pageSize, Long teacherId) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentCourseVo> studentCourseVos = teacherMapper.studentCourseListByTeacherId(teacherId);
        return new PageInfo<>(studentCourseVos);
    }
}
