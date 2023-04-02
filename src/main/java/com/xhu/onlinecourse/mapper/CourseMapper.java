package com.xhu.onlinecourse.mapper;

import com.xhu.onlinecourse.entity.Course;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {
    List<CourseTeacherVo> getCourseList();
    CourseTeacherVo getCourseById(@Param("courseId") Long courseId);
    int courseInsert(Course course);

    List<CourseTeacherVo> getStudentCourseList(@Param("studentId") Long studentId);
}
