package com.xhu.onlinecourse.mapper;


import com.xhu.onlinecourse.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
//    List<Student> studentList();
    List<Student> studentList(@Param("columnName") String name, @Param("value") String value);
    int studentInsert(Student student);
}
