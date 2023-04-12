package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Student;
import org.apache.ibatis.annotations.Param;

public interface StudentService {
    PageInfo<Student> studentList(int pageNum, int pageSize, String columnName, String value);//查询学生

    Integer studentInsert(Student student);//增加学生

    Integer studentDelete(Long studentId);//删除学生

    Integer studentUpdate(Student student);//修改学生
}
