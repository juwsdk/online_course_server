package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Teacher;

public interface TeacherService {
    PageInfo<Teacher> teacherList(int pageNum,int pageSize);
}
