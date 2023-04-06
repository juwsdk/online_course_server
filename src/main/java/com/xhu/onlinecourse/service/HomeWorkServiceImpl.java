package com.xhu.onlinecourse.service;

import com.xhu.onlinecourse.entity.CourseHomework;
import com.xhu.onlinecourse.entity.CourseRes;
import com.xhu.onlinecourse.mapper.HomeworkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HomeWorkServiceImpl implements HomeWorkService{
    @Autowired
    private HomeworkMapper homeworkMapper;

    @Override
    public List<CourseHomework> homeworkListByCourseId(Long courseId) {
        return homeworkMapper.homeworkListByCourseId(courseId);
    }
}
