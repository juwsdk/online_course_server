package com.xhu.onlinecourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
教师给学生作业的评分表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseHomeworkScore {
    private Long courseHomeworkScoreId;
    private Long courseHomeworkSubmitId;
    private Integer courseHomeworkScore;
}
