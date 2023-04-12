package com.xhu.onlinecourse.mapper;

import com.xhu.onlinecourse.entity.Course;
import com.xhu.onlinecourse.entity.CourseRes;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {
    List<CourseTeacherVo> getCourseList();//获取所有课程

    Integer countCourses();//获取课程总数

    CourseTeacherVo getCourseById(@Param("courseId") Long courseId);//通过课程id获取教师及课程相关信息

    int courseInsert(Course course);//增加一门课程

    List<CourseTeacherVo> getStudentCourseList(@Param("studentId") Long studentId);//获取指定学生Id的课程列表

    Integer countStudentCourses(@Param("studentId") Long studentId);//统计指定学生Id获取了多少课程数

    List<CourseRes> getCourseResList(@Param("courseId") Long courseId);//根据课程id获取课程资源

    int addNewCourseRes(CourseRes courseRes);//为课程增加课程资源

    List<CourseTeacherVo> getTopThreeCourseList();//选取选课榜前三，作为前端的走马灯
}
