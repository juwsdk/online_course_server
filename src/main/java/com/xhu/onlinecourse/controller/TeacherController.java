package com.xhu.onlinecourse.controller;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Teacher;
import com.xhu.onlinecourse.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @RequestMapping("/teacherList")
    public PageInfo<Teacher> teacherList(@RequestParam(defaultValue = "1") int pageNum,
                                         @RequestParam(defaultValue = "10") int pageSize){
        return teacherService.teacherList(pageNum,pageSize);
    }
}
