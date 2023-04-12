package com.xhu.onlinecourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
提问和回答表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseQuestions {
    private Long courseQuestionId;
    private Long courseId;
    private Long studentId;
    private String courseQuestionInfo;
    private Integer courseQuestionType;
    private Long parentQuestionId;
}
