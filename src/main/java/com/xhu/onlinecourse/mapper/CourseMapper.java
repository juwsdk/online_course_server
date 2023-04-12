package com.xhu.onlinecourse.mapper;

import com.xhu.onlinecourse.entity.Course;
import com.xhu.onlinecourse.entity.CourseRes;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {
    //获取所有课程
    List<CourseTeacherVo> getCourseList();

    //获取课程总数
    Integer countCourses();

    //通过课程id获取教师及课程相关信息
    CourseTeacherVo getCourseById(@Param("courseId") Long courseId);

    //增加一门课程
    int courseInsert(Course course);

    //获取指定学生Id的课程列表
    List<CourseTeacherVo> getStudentCourseList(@Param("studentId") Long studentId);

    //统计指定学生Id获取了多少课程数
    Integer countStudentCourses(@Param("studentId") Long studentId);

    //根据课程id获取课程资源
    List<CourseRes> getCourseResList(@Param("courseId") Long courseId);

    //为课程增加课程资源
    int addNewCourseRes(CourseRes courseRes);

    //选取选课榜前三，作为前端的走马灯
    List<CourseTeacherVo> getTopThreeCourseList();
}
