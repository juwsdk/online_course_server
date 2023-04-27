package com.xhu.onlinecourse.mapper;


import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.entity.vo.StudentAttendanceVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    //查询所有学生
    List<Student> studentList(@Param("columnName") String name, @Param("value") String value);

    //根据id查询学生
    Student studentById(@Param("studentId") Long studentId);

    //增加学生
    Integer studentInsert(Student student);

    //获取学号
    Long studentNumber(Student student);

    //删除学生
    Integer studentDelete(Student student);

    //修改学生
    Integer studentUpdate(Student student);

    //学生作业完成情况
    Integer studentHomeWorkIsFinished(@Param("studentId") Long studentId);

    //学生选修所有课程的作业
    Integer studentChooseAllHomeworks(@Param("studentId") Long studentId);

    //学生提出的问题
    Integer studentAskQuestions(@Param("studentId") Long studentId);

    //总的问题数
    Integer allQuestions();

    //学生最近三周打卡情况
    StudentAttendanceVo studentAttendance(@Param("studentId") Long studentId);

}
