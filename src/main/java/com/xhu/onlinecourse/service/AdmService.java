package com.xhu.onlinecourse.service;

import com.github.pagehelper.PageInfo;
import com.xhu.onlinecourse.entity.Adm;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdmService {
    PageInfo<Adm> admList(int pageNum, int pageSize, String columnName, String value);

}
