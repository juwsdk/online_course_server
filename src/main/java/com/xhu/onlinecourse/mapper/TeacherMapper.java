package com.xhu.onlinecourse.mapper;

import com.xhu.onlinecourse.entity.*;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;
import com.xhu.onlinecourse.entity.vo.StudentCourseVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TeacherMapper {
    //查询所有教师
    List<Teacher> teacherList(@Param("columnName") String name, @Param("value") String value);

    //删除教师
    Integer teacherDelete(Teacher teacher);

    //修改教师信息
    Integer teacherUpdate(Teacher teacher);

    //添加教师
    Integer teacherInsert(Teacher teacher);

    //查询教师信息
    Teacher teacherOne(@Param("teacherId") Long teacherId);

    //查询教师教授的所有课程
    List<CourseTeacherVo> teacherListById(@Param("teacherId") Long teacherId);

    //教师增加一门课程
    Integer courseInsert(Course course);

    //查询教师的课程id
    Long getCourseId(Course course);

    //查询教师教授的学生信息
    List<Student> studentListByTeacherCourseId(@Param("teacherId") Long teacherId, @Param("courseId") Long courseId);

    //查询教师所有开课所有学生选择的信息
    List<StudentCourseVo> studentCourseListByTeacherId(@Param("teacherId") Long teacherId);

    //教师布置作业，写入数据库
    Integer addHomeWork(CourseHomework courseHomework);

    //查询教师教授的课程一共有多少学生选
    Integer teacherCourseStudentSelectNum(@Param("teacherId") Long teacherId);

    //查询教师教授的课程以及选课人数
    @MapKey("name")
    List<Map<String, Integer>> teacherTeachStudentCourseCount(@Param("teacherId") Long teacherId);

    //查询教师上传的文件列表
    List<CourseRes> teacherResById(@Param("courseId") Long courseId);

    //将上传的课程视频信息写入数据库
    Integer teacherAddRes(CourseRes courseRes);

    //修改教师的课程的课程资源
    Integer teacherResAlter(CourseRes courseRes);

    //删除一条课程资源
    Integer teacherDeleteRes(@Param("courseResId") Long courseResId);

    //删除所有课程
    Integer teacherDeleteAllRes(@Param("courseId") Long courseId);
}
