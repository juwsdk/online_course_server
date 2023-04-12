package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.CourseAttendance;
import com.xhu.onlinecourse.entity.CourseQuestions;

import java.util.List;

public interface CourseQuestionsService {
    List<CourseQuestions> getCourseQuestionList(Long courseId);//查询提问列表

    List<CourseQuestions> getCourseQuestionChildList(Long parentQuestionId);//查询回答列表

    Integer studentAskQuestion(CourseQuestions courseQuestions);//插入一条评论

    Integer studentClockIn(CourseAttendance courseAttendance);//学生打卡

    PageInfo<CourseAttendance> allStudentIsClock(Long courseId, int pageNum, int pageSize, String name, String value);//教师查询所有学生打卡信息
}
