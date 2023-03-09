package com.xhu.onlinecourse.controller;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @RequestMapping("/studentList")
    public PageInfo<Student> studentList(@RequestParam(defaultValue = "1") int pageNum,
                                           @RequestParam(defaultValue = "10") int pageSize){
        return studentService.studentList(pageNum,pageSize);
    }
    @RequestMapping(value = "/studentInsert",method = RequestMethod.POST)
    public Integer studentInsert(@RequestBody Student student){
        System.out.println(student);
        return 1;
    }
}
