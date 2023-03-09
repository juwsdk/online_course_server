package com.xhu.onlinecourse.controller;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Adm;
import com.xhu.onlinecourse.service.AdmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdmController {
    @Autowired
    private AdmServiceImpl admService;

    @RequestMapping("/admList")
    public PageInfo<Adm> admList(@RequestParam(defaultValue = "1") int pageNum,
                                 @RequestParam(defaultValue = "10") int pageSize){
        return admService.admList(pageNum,pageSize);
    }
}
