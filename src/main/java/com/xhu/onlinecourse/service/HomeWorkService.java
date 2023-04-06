package com.xhu.onlinecourse.service;

import com.xhu.onlinecourse.entity.CourseHomework;
import com.xhu.onlinecourse.entity.CourseRes;

import java.util.List;

public interface HomeWorkService {
    List<CourseHomework> homeworkListByCourseId(Long courseId);
}
