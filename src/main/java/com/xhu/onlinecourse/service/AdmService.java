package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Adm;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdmService {
    //查看管理员列表
    PageInfo<Adm> admList(int pageNum, int pageSize, String columnName, String value);

    //获得管理员信息
    Adm getAdmById(Long admId);

    //增加管理员
    Integer admInsert(Adm adm);

    //删除管理员
    Integer admDelete(Adm adm);

    //更新管理员信息
    Integer admUpdate(Adm adm);

}
