package com.xhu.onlinecourse.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.CourseAttendance;
import com.xhu.onlinecourse.entity.CourseQuestions;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.entity.vo.CourseAttendanceVo;
import com.xhu.onlinecourse.mapper.CourseQuestionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseQuestionsServiceImpl implements CourseQuestionsService {
    @Autowired
    private CourseQuestionsMapper questionsMapper;

    @Override
    public List<CourseQuestions> getCourseQuestionList(Long courseId) {
        return questionsMapper.getCourseQuestionList(courseId);
    }

    @Override
    public List<CourseQuestions> getCourseQuestionChildList(Long parentQuestionId) {
        return questionsMapper.getCourseQuestionChildList(parentQuestionId);
    }

    @Override
    public Integer studentAskQuestion(CourseQuestions courseQuestions) {
        return questionsMapper.studentAskQuestion(courseQuestions);
    }

    @Override
    public Integer studentClockIn(CourseAttendance courseAttendance) {
        CourseAttendance isClock = questionsMapper.studentIsClock(courseAttendance.getStudentId(), courseAttendance.getCourseAttDate());
        System.err.println(isClock);
        if (isClock != null)
            return 2;// 1 表示成功打卡，2表示已经打卡，0表示打卡失败
        return questionsMapper.studentClockIn(courseAttendance);
    }

    @Override
    public PageInfo<CourseAttendance> allStudentIsClock(Long courseId, int pageNum, int pageSize, String name, String value) {
        PageHelper.startPage(pageNum, pageSize);
        List<CourseAttendanceVo> attendances = questionsMapper.allStudentIsClock(courseId, name, value);
        return new PageInfo<>(attendances);
    }
}
