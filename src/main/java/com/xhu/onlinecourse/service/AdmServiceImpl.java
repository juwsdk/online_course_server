package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<Adm> admList(int pageNum, int pageSize, String columnName, String value) {
        PageHelper.startPage(pageNum, pageSize);
        List<Adm> admList = admMapper.getAdmList(columnName, value);
        return new PageInfo<>(admList);
    }

}
