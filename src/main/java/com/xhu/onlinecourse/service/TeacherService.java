package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.CourseHomework;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.entity.Teacher;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;
import com.xhu.onlinecourse.entity.vo.StudentCourseVo;


import java.util.List;
import java.util.Map;

public interface TeacherService {
    //分页查询
    PageInfo<Teacher> teacherList(int pageNum, int pageSize, String columnName, String value);

    PageInfo<CourseTeacherVo> teacherListById(int pageNum, int pageSize, Long teacherId);//查询教师教授的所有课程

    PageInfo<Student> studentListByTeacherCourseId(Long teacherId, Long courseId, int pageNum, int pageSize);

    PageInfo<StudentCourseVo> studentCourseListByTeacherId(int pageNum, int pageSize, Long teacherId);

    Boolean addHomeWork(CourseHomework courseHomework);

    List<CourseTeacherVo> teacherListById(Long teacherId);//只查询教师教授的课程,不分页查询

    int teacherCourseStudentSelectNum(Long teacherId);//查询有多少学生选了这个教师的课

    Teacher teacherOne(Long teacherId);//查找一个教师的信息

    List<Map<String, Integer>> teacherTeachStudentCourseCount(Long teacherId);//查询教师教授的课程和选课人数

}
