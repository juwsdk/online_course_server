package com.xhu.onlinecourse.mapper;

import com.xhu.onlinecourse.entity.Adm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdmMapper {
    //查询管理员
    List<Adm> getAdmList(@Param("columnName") String name, @Param("value") String value);

    //通过id获得管理员信息
    Adm getAdmById(@Param("admId") Long admId);

    //增加管理员
    Integer admInsert(Adm adm);

    //删除管理员
    Integer admDelete(Adm adm);

    //更新管理员信息
    Integer admUpdate(Adm adm);
}
