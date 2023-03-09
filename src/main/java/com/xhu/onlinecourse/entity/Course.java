package com.xhu.onlinecourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Long course_id;
    private Long teacher_id;
    private String course_name;
    private String course_info;
    private String courseImage;
}
