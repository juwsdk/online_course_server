package com.xhu.onlinecourse.mapper;

import com.xhu.onlinecourse.entity.CourseHomework;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
处理作业相关的请求
 */
@Mapper
public interface HomeworkMapper {
    //查询课程对应的作业信息
    List<CourseHomework> homeworkListByCourseId(@Param("courseId") Long courseId);
}
