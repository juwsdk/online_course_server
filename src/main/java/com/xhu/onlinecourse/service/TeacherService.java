package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.*;
import com.xhu.onlinecourse.entity.aboutfile.CourseFile;
import com.xhu.onlinecourse.entity.aboutfile.FileData;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;
import com.xhu.onlinecourse.entity.vo.StudentCourseVo;
import org.apache.ibatis.annotations.Param;


import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TeacherService {
    //分页查询,查询教师信息
    PageInfo<Teacher> teacherList(int pageNum, int pageSize, String columnName, String value);

    //查询教师教授的所有课程,分页查询
    PageInfo<CourseTeacherVo> teacherListById(int pageNum, int pageSize, Long teacherId);

    //只查询教师教授的课程,不分页查询
    List<CourseTeacherVo> teacherListById(Long teacherId);

    //删除教师
    Integer teacherDelete(Teacher teacher);

    //修改教师信息
    Integer teacherUpdate(Teacher teacher);

    //添加教师
    Integer teacherInsert(Teacher teacher);

    //查找一个教师的信息
    Teacher teacherOne(Long teacherId);

    //教师新增一门课程
    Integer courseInsert(CourseFile course, String bathPath) throws IOException;

    //查询教师教授的学生信息
    PageInfo<Student> studentListByTeacherCourseId(Long teacherId, Long courseId, int pageNum, int pageSize);

    //查询教师所有开课所有学生选择的信息
    PageInfo<StudentCourseVo> studentCourseListByTeacherId(int pageNum, int pageSize, Long teacherId);

    //教师布置作业,向作业表插入数据
    Integer addHomeWork(CourseHomework courseHomework);

    //查询有多少学生选了这个教师的课
    Integer teacherCourseStudentSelectNum(Long teacherId);

    //查询教师教授的课程和选课人数
    List<Map<String, Integer>> teacherTeachStudentCourseCount(Long teacherId);

    /* 教师资源管理 */
    //查询教师上传的文件列表
    List<CourseRes> teacherResById(Long courseId);

    //将前端上传的文件写入数据库并创建相应的文件
    Integer teacherAddRes(FileData courseRes, String bathPath) throws IOException;

    //修改教师的课程资源
    Integer teacherResAlter(CourseRes courseRes);

    //删除一条课程资源
    Integer teacherDeleteRes(CourseRes courseRes, Long teacherId, String bathPath);

    //删除所有课程资源
    Integer teacherDeleteAllRes(Long coureseId, Long teacherId, String bathPath);

}
