package com.xhu.onlinecourse.mapper;

import com.xhu.onlinecourse.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface StudentMapper {
    List<Student> studentList();
    int studentInsert(Student student);
}
