package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.entity.vo.StudentAttendanceVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface StudentService {
    //查询学生
    PageInfo<Student> studentList(int pageNum, int pageSize, String columnName, String value);

    //增加学生
    Integer studentInsert(Student student);

    //删除学生
    Integer studentDelete(Long studentId);

    //修改学生
    Integer studentUpdate(Student student);

    //学生作业完成情况与总作业数
    Map<String, Integer> studentHomeWork(Long studentId);

    //学生提出的问题和总的问题数
    Map<String, Integer> studentQuestionsNum(Long studentId);

    //学生最近三周打卡情况
    StudentAttendanceVo studentAttendance(Long studentId);
}


