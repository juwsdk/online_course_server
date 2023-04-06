package com.xhu.onlinecourse.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseVo {
    private Long studentId;
    private String studentName;
    private String studentGender;
    private String studentEmail;
    private String courseName;
}
