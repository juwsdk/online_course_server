package com.xhu.onlinecourse.mapper;

import com.xhu.onlinecourse.entity.Course;
import com.xhu.onlinecourse.entity.CourseHomework;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.entity.Teacher;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;
import com.xhu.onlinecourse.entity.vo.StudentCourseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TeacherMapper {
    //查询所有教师
    List<Teacher> teacherList();
    //查询教师教授的课程
    List<CourseTeacherVo> teacherListById(@Param("teacherId") Long teacherId);
    //查询教师教授的学生信息
    List<Student> studentListByTeacherCourseId(@Param("teacherId") Long teacherId,@Param("courseId") Long courseId);
    //查询教师所有开课所有学生选择的信息
    List<StudentCourseVo> studentCourseListByTeacherId(@Param("teacherId") Long teacherId);
    //教师布置作业，写入数据库
    int addHomeWork(CourseHomework courseHomework);
}
