package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Student;

public interface StudentService {
    PageInfo<Student> studentList(int pageNum, int pageSize,String columnName,String value);
    int studentInsert(Student student);
}
