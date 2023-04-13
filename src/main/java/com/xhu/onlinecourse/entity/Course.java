package com.xhu.onlinecourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    protected Long courseId;
    protected Long teacherId;
    protected String courseName;
    protected String courseInfo;
    protected String courseImage;
}
