package com.xhu.onlinecourse.controller;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Adm;
import com.xhu.onlinecourse.service.AdmServiceImpl;
import com.xhu.onlinecourse.utils.Result;
import com.xhu.onlinecourse.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "管理员接口")
@RequiresRoles("admin")
@RestController
@RequestMapping("/admin")
public class AdmController {
    @Autowired
    private AdmServiceImpl admService;

    @ApiOperation(value = "查询所有管理员")
    @RequestMapping(value = "/admList")
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
    @RequestMapping(value = "/admDelete", method = RequestMethod.DELETE)
    public Result<Integer> admDelete(@RequestBody Adm adm) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), admService.admDelete(adm));
    }

    @ApiOperation(value = "增加一个管理员")
    @RequestMapping(value = "/admInsert", method = RequestMethod.POST)
    public Result<Integer> admInsert(@RequestBody Adm adm) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), admService.admUpdate(adm));
    }
}
