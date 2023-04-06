package com.xhu.onlinecourse.controller;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Student;
import com.xhu.onlinecourse.mapper.StudentMapper;
import com.xhu.onlinecourse.service.StudentService;
import com.xhu.onlinecourse.utils.Result;
import com.xhu.onlinecourse.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "学生接口")
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;
    @ApiOperation(value = "查询所有学生")
    @RequestMapping("/studentList")
    public Result<PageInfo<Student>> studentList(@RequestParam(defaultValue = "1") int pageNum,
                                       @RequestParam(defaultValue = "10") int pageSize,
                                       @RequestParam(name = "fuzzyColumn", required = false) String columnName,
                                       @RequestParam(name = "fuzzyValue", required = false) String value) {
        System.err.println(columnName);
        System.err.println(value);
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),
                studentService.studentList(pageNum, pageSize, columnName, value));
    }
    @ApiOperation(value = "添加学生")
    @RequestMapping(value = "/studentInsert", method = RequestMethod.POST)
    public Integer studentInsert(@RequestBody Student student) {
        System.out.println(student);
        return 1;
    }
}
