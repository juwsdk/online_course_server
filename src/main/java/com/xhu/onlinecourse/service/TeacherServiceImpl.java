package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.CourseHomework;
import com.xhu.onlinecourse.entity.CourseRes;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.entity.Teacher;
import com.xhu.onlinecourse.entity.aboutfile.FileData;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;
import com.xhu.onlinecourse.entity.vo.StudentCourseVo;
import com.xhu.onlinecourse.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public PageInfo<Teacher> teacherList(int pageNum, int pageSize, String columnName, String value) {
        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> teacherList = teacherMapper.teacherList(columnName, value);
        return new PageInfo<>(teacherList);
    }

    @Override
    public PageInfo<CourseTeacherVo> teacherListById(int pageNum, int pageSize, Long teacherId) {
        PageHelper.startPage(pageNum, pageSize);
        List<CourseTeacherVo> courseTeacherVos = teacherMapper.teacherListById(teacherId);
        return new PageInfo<>(courseTeacherVos);
    }

    @Override
    public PageInfo<Student> studentListByTeacherCourseId(Long teacherId, Long courseId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> students = teacherMapper.studentListByTeacherCourseId(teacherId, courseId);
        return new PageInfo<>(students);
    }

    @Override
    public Integer addHomeWork(CourseHomework courseHomework) {
        return teacherMapper.addHomeWork(courseHomework);
    }

    @Override
    public List<CourseTeacherVo> teacherListById(Long teacherId) {
        return teacherMapper.teacherListById(teacherId);
    }

    @Override
    public Integer teacherCourseStudentSelectNum(Long teacherId) {
        return teacherMapper.teacherCourseStudentSelectNum(teacherId);
    }

    @Override
    public Teacher teacherOne(Long teacherId) {
        return teacherMapper.teacherOne(teacherId);
    }

    @Override
    public List<Map<String, Integer>> teacherTeachStudentCourseCount(Long teacherId) {
        return teacherMapper.teacherTeachStudentCourseCount(teacherId);
    }

    @Override
    public List<CourseRes> teacherResById(Long courseId) {
        return teacherMapper.teacherResById(courseId);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)//这个注解用作测试，不提交事务
    @Override//将前端上传的文件写入数据库并创建相应的文件
    public Integer teacherAddRes(FileData courseRes, String bathPath) throws IOException {
        //获得文件，写入文件夹 static/{teacherId}/{courseId}/res
        String coursesPath = String.format("%s/%s/%s/res", bathPath, courseRes.getTeacherId(), courseRes.getCourseId());
        File srcFloder = new File(coursesPath);
        //不存在这个文件夹，则创建这个文件夹
        if (!srcFloder.exists()) {
            srcFloder.mkdirs();
        }
        File saveFile = new File(srcFloder, courseRes.getResFileName());
        //写入文件,如果有异常会抛出，不会继续执行
        courseRes.getFileRaw().transferTo(saveFile);
        //写入数据库
        if (teacherMapper.teacherAddRes(courseRes) == 1) {
            return 1;
        }
        return 0;
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)//这个注解用作测试，不提交事务
    @Override
    public Integer teacherResAlter(CourseRes courseRes) {
        return teacherMapper.teacherResAlter(courseRes);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public Integer teacherDeleteRes(Long courseResId) {
        return teacherMapper.teacherDeleteRes(courseResId);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public Integer teacherDeleteAllRes(Long courseId) {
        return teacherMapper.teacherDeleteAllRes(courseId);
    }

    @Override
    public PageInfo<StudentCourseVo> studentCourseListByTeacherId(int pageNum, int pageSize, Long teacherId) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentCourseVo> studentCourseVos = teacherMapper.studentCourseListByTeacherId(teacherId);
        return new PageInfo<>(studentCourseVos);
    }
}
