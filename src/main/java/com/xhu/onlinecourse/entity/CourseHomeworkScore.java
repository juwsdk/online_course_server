package com.xhu.onlinecourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseHomeworkScore {
    private Long courseHomeworkScoreId;
    private Long courseHomeworkSubmitId;
    private Integer courseHomeworkScore;
}
