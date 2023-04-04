package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.entity.Teacher;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;

public interface TeacherService {
    PageInfo<Teacher> teacherList(int pageNum,int pageSize);
    PageInfo<CourseTeacherVo> teacherListById(int pageNum,int pageSize,Long teacherId);
    PageInfo<Student> studentListByTeacherCourseId(Long teacherId, Long courseId,int pageNum,int pageSize);
}
