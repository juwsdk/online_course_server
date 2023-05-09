package com.xhu.onlinecourse.service;

import com.xhu.onlinecourse.entity.CourseHomework;
import com.xhu.onlinecourse.entity.CourseHomeworkScore;
import com.xhu.onlinecourse.entity.CourseHomeworkSubmit;
import com.xhu.onlinecourse.entity.aboutfile.HomeworkFileData;
import com.xhu.onlinecourse.entity.aboutfile.HomeworkSubmitFileData;
import com.xhu.onlinecourse.entity.vo.CourseSubmitVo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface HomeWorkService {
    //通过课程id查找作业
    List<CourseHomework> homeworkListByCourseId(Long courseId);

    //教师上传作业
    Integer teacherUploadHomework(HomeworkFileData courseHomework, String bathPath) throws IOException;

    //学生提交作业前是否还提交了作业
    CourseHomeworkSubmit studentSubmitHomeworkBefore(Long courseHomeworkId, Long studentId);

    //学生修改提交的作业
    Integer studentUpdateHomework(HomeworkSubmitFileData courseHomeworkSubmit, String bathPath) throws IOException;

    //学生提交作业
    Integer studentSubmitHomework(HomeworkSubmitFileData courseHomeworkSubmit, String bathPath) throws IOException;

    //查询这门课程学生完成人数
    List<Map<String, String>> studentCountFinish(Long courseHomeworkId);

    //展示学生上传的作业列表
    List<Map<String, CourseHomeworkSubmit>> homeworkSubmitByHomeworkId(Long courseHomeworkId);

    //没有上传作业的学生名字
    List<String> homeworkNotSubmitByHomeworkId(Long courseHomeworkId);

    //学生上传的作业教师下载
    String homeworkSubmitStudentOne(CourseSubmitVo courseSubmitVo);

    //教师给学生打分
    Integer scoreToStudent(CourseHomeworkScore courseHomeworkScore);

    //学生查看自己的得分
    Integer studentShowScore(Long courseHomeworkId, Long studentId);

}
