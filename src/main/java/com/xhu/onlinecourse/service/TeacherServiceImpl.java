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

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public PageInfo<Teacher> teacherList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> teacherList = teacherMapper.teacherList();
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
        if (teacherMapper.addHomeWork(courseHomework)==1)
            return true;
        return false;
    }

    @Override
    public PageInfo<StudentCourseVo> studentCourseListByTeacherId(int pageNum,int pageSize,Long teacherId) {
        PageHelper.startPage(pageNum,pageSize);
        List<StudentCourseVo> studentCourseVos = teacherMapper.studentCourseListByTeacherId(teacherId);
        return new PageInfo<>(studentCourseVos);
    }
}
