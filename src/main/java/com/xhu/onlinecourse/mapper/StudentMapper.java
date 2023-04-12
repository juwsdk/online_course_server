package com.xhu.onlinecourse.mapper;


import com.xhu.onlinecourse.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface StudentMapper {
    //    List<Student> studentList();
    List<Student> studentList(@Param("columnName") String name, @Param("value") String value);//查询所有学生

    Integer studentInsert(Student student);//增加学生

    Integer studentDelete(@Param("studentId") Long studentId);//删除学生

    Integer studentUpdate(Student student);//修改学生
}
