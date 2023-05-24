package com.xhu.onlinecourse.controller;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Adm;
import com.xhu.onlinecourse.entity.vo.admSta.StudentStaVo;
import com.xhu.onlinecourse.entity.vo.admSta.TeacherStaVo;
import com.xhu.onlinecourse.service.impl.AdmServiceImpl;
import com.xhu.onlinecourse.utils.Result;
import com.xhu.onlinecourse.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "管理员接口")
//@RequiresRoles("admin")
@RestController
@RequestMapping("/admin")
public class AdmController {
    @Autowired
    private AdmServiceImpl admService;

    @ApiOperation(value = "查询所有管理员")
    @RequestMapping(value = "/admList", method = RequestMethod.GET)
    public Result<PageInfo<Adm>> admList(@RequestParam(defaultValue = "1") int pageNum,
                                         @RequestParam(defaultValue = "10") int pageSize,
                                         @RequestParam(name = "fuzzyColumn", required = false) String columnName,
                                         @RequestParam(name = "fuzzyValue", required = false) String value) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), admService.admList(pageNum, pageSize, columnName, value));
    }

    @ApiOperation(value = "根据id获得管理员信息")
    @RequestMapping(value = "/{admId}/admOne", method = RequestMethod.POST)
    public Result<Adm> admUpdate(@PathVariable Long admId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), admService.getAdmById(admId));
    }

    @ApiOperation(value = "修改管理员信息")
    @RequestMapping(value = "/admUpdate", method = RequestMethod.PUT)
    public Result<Integer> admUpdate(@RequestBody Adm adm) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), admService.admUpdate(adm));
    }

    @ApiOperation(value = "删除管理员")
    @RequestMapping(value = "/admDelete", method = RequestMethod.POST)
    public Result<Integer> admDelete(@RequestBody Adm adm) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), admService.admDelete(adm));
    }

    @ApiOperation(value = "增加一个管理员")
    @RequestMapping(value = "/admInsert", method = RequestMethod.POST)
    public Result<Integer> admInsert(@RequestBody Adm adm) {
        System.err.println(adm);
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), admService.admInsert(adm));
    }

    @ApiOperation(value = "统计教师课程展示")
    @RequestMapping(value = "/staTeacher", method = RequestMethod.GET)
    public Result<PageInfo<TeacherStaVo>> staTeacher(@RequestParam(defaultValue = "1") int pageNum,
                                                     @RequestParam(defaultValue = "10") int pageSize,
                                                     @RequestParam(name = "fuzzyColumn", required = false) String columnName,
                                                     @RequestParam(name = "fuzzyValue", required = false) String value) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), admService.teacherSta(pageNum, pageSize, columnName, value));
    }

    @ApiOperation(value = "根据教师ID统计课程展示")
    @RequestMapping(value = "/{teacherId}/staTeacher", method = RequestMethod.GET)
    public Result<List<String>> staTeacherByID(@PathVariable Long teacherId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), admService.teacherStaById(teacherId));
    }

    @ApiOperation(value = "统计学生课程展示")
    @RequestMapping(value = "/staStudent", method = RequestMethod.GET)
    public Result<PageInfo<StudentStaVo>> staStudent(@RequestParam(defaultValue = "1") int pageNum,
                                                     @RequestParam(defaultValue = "10") int pageSize,
                                                     @RequestParam(name = "fuzzyColumn", required = false) String columnName,
                                                     @RequestParam(name = "fuzzyValue", required = false) String value) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), admService.studentSta(pageNum, pageSize, columnName, value));
    }

    @ApiOperation(value = "根据学生ID统计课程展示")
    @RequestMapping(value = "/{studentId}/staStudent", method = RequestMethod.GET)
    public Result<List<String>> staStudentByID(@PathVariable Long studentId) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), admService.studentStaById(studentId));
    }
}
