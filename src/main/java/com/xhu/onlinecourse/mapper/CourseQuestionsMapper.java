package com.xhu.onlinecourse.mapper;

import com.xhu.onlinecourse.entity.CourseAttendance;
import com.xhu.onlinecourse.entity.CourseQuestions;
import com.xhu.onlinecourse.entity.vo.CourseAttendanceVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

/*
处理问题问答和学生打卡
 */
@Mapper
public interface CourseQuestionsMapper {
    List<CourseQuestions> getCourseQuestionList(@Param("courseId") Long courseId);//查询提问列表

    List<CourseQuestions> getCourseQuestionChildList(@Param("parentQuestionId") Long parentQuestionId); //展示评论的子评论

    Integer studentAskQuestion(CourseQuestions courseQuestions);//插入一条评论

    Integer studentClockIn(CourseAttendance courseAttendance);//学生当天打卡

    CourseAttendance studentIsClock(@Param("studentId") Long studentId, @Param("courseAttDate") Date courseAttDate);//查询该学生是否打卡

    List<CourseAttendanceVo> allStudentIsClock(@Param("courseId") Long courseId, @Param("columnName") String name, @Param("value") String value);//教师查询所有打卡信息
}
