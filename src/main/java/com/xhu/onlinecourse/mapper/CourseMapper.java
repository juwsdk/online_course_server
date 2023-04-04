package com.xhu.onlinecourse.mapper;

import com.xhu.onlinecourse.entity.Course;
import com.xhu.onlinecourse.entity.CourseRes;
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
    List<CourseRes> getCourseResList(@Param("courseId") Long courseId);
    int addNewCourseRes(CourseRes courseRes);
    //选取选课榜前三，作为前端的走马灯
    List<CourseTeacherVo> getTopThreeCourseList();
}
