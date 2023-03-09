package com.xhu.onlinecourse.mapper;

import com.xhu.onlinecourse.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TeacherMapper {
    List<Teacher> teacherList();
}
