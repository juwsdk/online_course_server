package com.xhu.onlinecourse.mapper;

import com.xhu.onlinecourse.entity.CourseHomework;
import com.xhu.onlinecourse.entity.CourseHomeworkScore;
import com.xhu.onlinecourse.entity.CourseHomeworkSubmit;
import com.xhu.onlinecourse.entity.Student;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/*
处理作业相关的请求
 */
@Mapper
public interface HomeworkMapper {
    //查询课程对应的作业信息
    List<CourseHomework> homeworkListByCourseId(@Param("courseId") Long courseId);

    //教师上传作业
    Integer teacherUploadHomework(CourseHomework courseHomework);

    //学生上传作业列表
    @MapKey("studentName")
    List<Map<String, CourseHomeworkSubmit>> homeworkSubmitByHomeworkId(@Param("courseHomeworkId") Long courseHomeworkId);

    //查看指定学生上传的作业
    CourseHomeworkSubmit homeworkSubmitStudentOne(@Param("courseHomeworkSubmitId") Long courseHomeworkSubmitId);

    //学生提交作业
    Integer studentSubmitHomework(CourseHomeworkSubmit courseHomeworkSubmit);

    //查询这门课程指定作业完成的学生
    @MapKey("studentId")
    List<Map<String, String>> studentCountFinish(@Param("courseHomeworkId") Long courseHomeworkId);

    //没有上传作业的学生列表
    List<String> homeworkNotSubmitByHomeworkId(@Param("courseHomeworkId") Long courseHomeworkId);

    //教师给学生打分
    Integer scoreToStudent(CourseHomeworkScore courseHomeworkScore);

    //查看得分
    Integer studentShowScore(@Param("courseHomeworkId") Long courseHomeworkId, @Param("studentId") Long studentId);

    //教师删除作业,删除三个表的数据
    Integer homeworkDelete(@Param("courseHomeworkId") Long courseHomeworkId);//删除courseHomework表

    Integer homeworkSubmitDelete(@Param("courseHomeworkId") Long courseHomeworkId);//删除courseHomeworkSubmit表

    Integer homeworkScoreDelete(@Param("courseHomeworkId") Long courseHomeworkId);//删除courseHomeworkScore表


}
