package com.xhu.onlinecourse.controller;

import com.xhu.onlinecourse.entity.Adm;
import com.xhu.onlinecourse.service.AdmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdmController {
    @Autowired
    private AdmServiceImpl admService;

    @ResponseBody
    @RequestMapping("/getAllAdm")
    public List<Adm> getAllAdm(){
        return admService.getAllAdm();
    }
}
