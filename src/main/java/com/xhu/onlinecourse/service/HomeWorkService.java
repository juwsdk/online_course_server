package com.xhu.onlinecourse.service;

import com.xhu.onlinecourse.entity.CourseHomework;
import com.xhu.onlinecourse.entity.aboutfile.HomeworkFileData;
import com.xhu.onlinecourse.entity.aboutfile.HomeworkSubmitFileData;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface HomeWorkService {
    //通过课程id查找作业
    List<CourseHomework> homeworkListByCourseId(Long courseId);

    //教师上传作业
    Integer teacherUploadHomework(HomeworkFileData courseHomework, String bathPath) throws IOException;

    //学生提交作业
    Integer studentSubmitHomework(HomeworkSubmitFileData courseHomeworkSubmit, String bathPath) throws IOException;

    //查询这门课程学生完成人数
    List<Map<String, String>> studentCountFinish(Long courseHomeworkId);
}
