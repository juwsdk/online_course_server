package com.xhu.onlinecourse.service;

import com.xhu.onlinecourse.entity.Adm;
import com.xhu.onlinecourse.mapper.AdmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdmServiceImpl implements AdmService{

    @Autowired
    private AdmMapper admMapper;
    @Override
    public List<Adm> getAllAdm() {
        System.out.println(admMapper.getAllAdm());
        return admMapper.getAllAdm();
    }
}
