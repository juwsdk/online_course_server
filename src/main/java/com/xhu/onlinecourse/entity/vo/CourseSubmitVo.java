package com.xhu.onlinecourse.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseSubmitVo {
    private Long teacherId;
    private Long courseId;
    private Long studentId;
    private Long courseHomeworkSubmitId;
}
