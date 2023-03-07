package com.xhu.onlinecourse.mapper;

import com.xhu.onlinecourse.entity.Adm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdmMapper {
    List<Adm> getAllAdm();
}
