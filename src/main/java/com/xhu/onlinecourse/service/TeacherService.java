package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.CourseHomework;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.entity.Teacher;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;
import com.xhu.onlinecourse.entity.vo.StudentCourseVo;

public interface TeacherService {
    PageInfo<Teacher> teacherList(int pageNum,int pageSize);
    PageInfo<CourseTeacherVo> teacherListById(int pageNum,int pageSize,Long teacherId);
    PageInfo<Student> studentListByTeacherCourseId(Long teacherId, Long courseId,int pageNum,int pageSize);
    Boolean addHomeWork(CourseHomework courseHomework);
    PageInfo<StudentCourseVo> studentCourseListByTeacherId(int pageNum,int pageSize,Long teacherId);
}
