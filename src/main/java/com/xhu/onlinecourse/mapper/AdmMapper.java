package com.xhu.onlinecourse.mapper;

import com.xhu.onlinecourse.entity.Adm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdmMapper {
    List<Adm> getAdmList(@Param("columnName") String name, @Param("value") String value);//查询管理员

    Integer admInsert(Adm adm);//增加管理员

    Integer admDelete(@Param("admId") Long admId);//删除管理员

    Integer admUpdate(Adm adm);//更新管理员信息
}
