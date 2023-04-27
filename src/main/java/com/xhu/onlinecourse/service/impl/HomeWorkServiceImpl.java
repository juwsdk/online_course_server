package com.xhu.onlinecourse.service.impl;

import com.xhu.onlinecourse.entity.CourseHomework;
import com.xhu.onlinecourse.entity.CourseHomeworkScore;
import com.xhu.onlinecourse.entity.CourseHomeworkSubmit;
import com.xhu.onlinecourse.entity.aboutfile.HomeworkFileData;
import com.xhu.onlinecourse.entity.aboutfile.HomeworkSubmitFileData;
import com.xhu.onlinecourse.entity.vo.CourseSubmitVo;
import com.xhu.onlinecourse.mapper.HomeworkMapper;
import com.xhu.onlinecourse.service.HomeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class HomeWorkServiceImpl implements HomeWorkService {
    @Autowired
    private HomeworkMapper homeworkMapper;

    @Override
    public List<CourseHomework> homeworkListByCourseId(Long courseId) {
        return homeworkMapper.homeworkListByCourseId(courseId);
    }

    @Override
    public Integer teacherUploadHomework(HomeworkFileData courseHomework, String bathPath) throws IOException {
        //获得文件，写入文件夹 static/{teacherId}/{courseId}/homework/homeworkDistribute
        String coursesPath = String.format("%s/%s/%s/homework/homeworkDistribute", bathPath, courseHomework.getTeacherId(), courseHomework.getCourseId());
        File srcFloder = new File(coursesPath);
        //不存在这个文件夹，则创建这个文件夹
        if (!srcFloder.exists()) {
            srcFloder.mkdirs();
        }
        File saveFile = new File(srcFloder, courseHomework.getCourseHomeworkRes());
        //写入文件,如果有异常会抛出，不会继续执行
        courseHomework.getFileRaw().transferTo(saveFile);
        //写入数据库
        if (homeworkMapper.teacherUploadHomework(courseHomework) == 1) {
            return 1;
        }
        return 0;
    }

    @Override
    public Integer studentSubmitHomework(HomeworkSubmitFileData courseHomeworkSubmit, String bathPath) throws IOException {
        //获得文件，写入文件夹 static/{teacherId}/{courseId}/homework/homeworkSubmit
        String coursesPath = String.format("%s/%s/%s/homework/homeworkSubmit", bathPath, courseHomeworkSubmit.getTeacherId(), courseHomeworkSubmit.getCourseId());
        File srcFloder = new File(coursesPath);
        //不存在这个文件夹，则创建这个文件夹
        if (!srcFloder.exists()) {
            srcFloder.mkdirs();
        }
        File saveFile = new File(srcFloder, courseHomeworkSubmit.getCourseHomeworkRes());
        //写入文件,如果有异常会抛出，不会继续执行
        courseHomeworkSubmit.getFileRaw().transferTo(saveFile);
        //写入数据库
        if (homeworkMapper.studentSubmitHomework(courseHomeworkSubmit) == 1) {
            return 1;
        }
        return 0;
    }

    @Override
    public List<Map<String, String>> studentCountFinish(Long courseHomeworkId) {
        return homeworkMapper.studentCountFinish(courseHomeworkId);
    }

    @Override//展示学生上传的作业列表
    public List<Map<String, CourseHomeworkSubmit>> homeworkSubmitByHomeworkId(Long courseHomeworkId) {
        return homeworkMapper.homeworkSubmitByHomeworkId(courseHomeworkId);
    }

    @Override//没有上传作业的学生名字
    public List<String> homeworkNotSubmitByHomeworkId(Long courseHomeworkId) {
        return homeworkMapper.homeworkNotSubmitByHomeworkId(courseHomeworkId);
    }

    @Override//学生上传的作业教师下载
    public String homeworkSubmitStudentOne(CourseSubmitVo courseSubmitVo) {
        return homeworkMapper.homeworkSubmitStudentOne(courseSubmitVo.getCourseHomeworkSubmitId()).getCourseHomeworkRes();
    }

    @Override//教师给学生打分
    public Integer scoreToStudent(CourseHomeworkScore courseHomeworkScore) {
        return homeworkMapper.scoreToStudent(courseHomeworkScore);
    }

    @Override//学生查看自己的得分
    public Integer studentShowScore(Long courseHomeworkId, Long studentId) {
        return homeworkMapper.studentShowScore(courseHomeworkId, studentId);
    }
}
