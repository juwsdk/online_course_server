package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Adm;
import com.xhu.onlinecourse.mapper.AdmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdmServiceImpl implements AdmService {

    @Autowired
    private AdmMapper admMapper;

    @Override//查看管理员列表
    public PageInfo<Adm> admList(int pageNum, int pageSize, String columnName, String value) {
        PageHelper.startPage(pageNum, pageSize);
        List<Adm> admList = admMapper.getAdmList(columnName, value);
        return new PageInfo<>(admList);
    }

    @Override//通过id获取管理员信息
    public Adm getAdmById(Long admId) {
        return admMapper.getAdmById(admId);
    }

    @Override//增加管理员
    public Integer admInsert(Adm adm) {
        return admMapper.admInsert(adm);
    }

    @Override//删除管理员
    public Integer admDelete(Adm adm) {
        return admMapper.admDelete(adm);
    }

    @Override//更新管理员
    public Integer admUpdate(Adm adm) {
        return admMapper.admUpdate(adm);
    }

}
