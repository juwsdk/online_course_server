package com.xhu.onlinecourse.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.*;
import com.xhu.onlinecourse.entity.aboutfile.CourseFile;
import com.xhu.onlinecourse.entity.aboutfile.FileData;
import com.xhu.onlinecourse.entity.vo.CourseTeacherVo;
import com.xhu.onlinecourse.entity.vo.StudentCourseVo;
import com.xhu.onlinecourse.mapper.TeacherMapper;
import com.xhu.onlinecourse.service.TeacherService;
import com.xhu.onlinecourse.utils.FileUtil;
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
    public PageInfo<Student> studentListByTeacherCourseId(int pageNum, int pageSize, String columnName, String value, Long teacherId, Long courseId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> students = teacherMapper.studentListByTeacherCourseId(teacherId, courseId, columnName, value);
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

    @Override//删除教师
    public Integer teacherDelete(Teacher teacher) {
        Integer integer = teacherMapper.teacherDelete(teacher);
        System.out.println(integer);
        return integer;
    }

    @Override//修改教师信息
    public Integer teacherUpdate(Teacher teacher) {
        return teacherMapper.teacherUpdate(teacher);
    }

    @Override//增加教师
    public Integer teacherInsert(Teacher teacher) {
        return teacherMapper.teacherInsert(teacher);
    }

    @Override
    public Integer teacherCourseStudentSelectNum(Long teacherId) {
        return teacherMapper.teacherCourseStudentSelectNum(teacherId);
    }


    @Override
    public Teacher teacherOne(Long teacherId) {
        return teacherMapper.teacherOne(teacherId);
    }

    @Transactional//开启事务
    @Override//教师新增一门课程
    public Integer courseInsert(CourseFile course, String bathPath) throws IOException {
        if (teacherMapper.courseInsert(course) == 0) {//先写入数据库再查询课程id创建文件夹
            return 0;
        }
        Long courseId = teacherMapper.getCourseId(course);
        String coursesPath = String.format("%s/%s", bathPath, course.getTeacherId(), courseId);
        File srcFloder = new File(coursesPath);
        //不存在这个文件夹，则创建这个文件夹
        if (!srcFloder.exists()) {
            srcFloder.mkdirs();
        }
        //上传了图片资源
        if (course.getFileRaw() != null) {
            File saveFile = new File(srcFloder, course.getFileRaw().getOriginalFilename());
            System.err.println(saveFile);
            //将图片写入 /{teacherId}/{courseId} 文件夹下
            course.getFileRaw().transferTo(saveFile);
        }
        String[] resFloder = {"/res", "/homework/homeworkDistribute", "/homework/homeworkSubmit"};
        for (String s : resFloder) {
            File floder = new File(coursesPath + s);
            if (!floder.exists())
                floder.mkdir();
        }
        return 1;//IO也没有异常，说明操作成功
    }

    @Override//教师删除一门课程
    public Integer courseDelete(Long courseId) {
        return teacherMapper.courseDelete(courseId);
    }

    @Override//查询教师的一门课程
    public Course getCourseInfoById(Long courseId) {
        return teacherMapper.getCourseInfoById(courseId);
    }

    @Override//教师修改一门课程的课程信息
    public Integer courseUpdate(CourseFile course, String bathPath) throws IOException {
        if (teacherMapper.courseUpdate(course) == 0) {//先写入数据库再查询课程id创建文件夹
            return 0;
        }
        Long courseId = teacherMapper.getCourseId(course);
        String coursesPath = String.format("%s/%s/%s", bathPath, course.getTeacherId(), courseId);
        File srcFloder = new File(coursesPath);
        //不存在这个文件夹，则创建这个文件夹
        if (!srcFloder.exists()) {
            srcFloder.mkdirs();
        }
        //上传了图片资源
        if (course.getFileRaw() != null) {
            File saveFile = new File(srcFloder, course.getFileRaw().getOriginalFilename());
            System.err.println(saveFile);
            //将图片写入 /{teacherId}/{courseId} 文件夹下
            course.getFileRaw().transferTo(saveFile);
        }
        return 1;//IO也没有异常，说明操作成功
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

    @Override//修改课程资源的信息
    public Integer teacherResAlter(CourseRes courseRes) {
        return teacherMapper.teacherResAlter(courseRes);
    }

    @Transactional
    @Override//删除一个课程资源
    public Integer teacherDeleteRes(CourseRes courseRes, Long teacherId, String bathPath) {
        String coursesPath = String.format("%s/%s/%s/res", bathPath, teacherId, courseRes.getCourseId());
        File targetFile = new File(coursesPath, courseRes.getResFileName());
        if (targetFile.exists()) {
            targetFile.delete();
        } else {
            return 0;//没有这个文件，返回删除失败
        }
        //更新数据库
        Integer integer = teacherMapper.teacherDeleteRes(courseRes.getCourseResId());
        System.out.println(integer);
        return integer;
    }

    @Transactional
    @Override
    public Integer teacherDeleteAllRes(Long courseId, Long teacherId, String bathPath) {
        String coursesPath = String.format("%s/%s/%s/res", bathPath, teacherId, courseId);
        File targetfloder = new File(coursesPath);
        if (targetfloder.exists()) {
            //递归删除这个文件夹下的所有文件
            FileUtil.deleteAllFiles(targetfloder);
            //文件夹删除了则再创建一个
            if (!targetfloder.exists())
                targetfloder.mkdir();
        } else {
            return 0;//没有这个文件夹，返回删除失败
        }
        //更新数据库
        return teacherMapper.teacherDeleteAllRes(courseId);
    }

    @Override//教师删除指定的学生
    public Integer studentRemove(Long courseId, StudentCourseVo studentCourseVo) {
        return teacherMapper.studentRemove(courseId,studentCourseVo.getStudentId());
    }

    @Override
    public PageInfo<StudentCourseVo> studentCourseListByTeacherId(int pageNum, int pageSize, String columnName, String value, Long teacherId) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentCourseVo> studentCourseVos = teacherMapper.studentCourseListByTeacherId(teacherId, columnName, value);
        return new PageInfo<>(studentCourseVos);
    }
}
