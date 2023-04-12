package com.xhu.onlinecourse.service;

import com.xhu.onlinecourse.entity.CourseHomework;
import com.xhu.onlinecourse.entity.aboutfile.HomeworkFileData;
import com.xhu.onlinecourse.entity.aboutfile.HomeworkSubmitFileData;
import com.xhu.onlinecourse.mapper.HomeworkMapper;
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
}
