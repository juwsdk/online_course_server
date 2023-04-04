package com.xhu.onlinecourse.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseTeacherVo {
    private Long courseId;
    private Long teacherId;
    private String courseName;
    private String teacherName;
    private String courseInfo;
    private String courseImage;
}
