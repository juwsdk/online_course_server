package com.xhu.onlinecourse.mapper;

import com.xhu.onlinecourse.entity.Adm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdmMapper {
    List<Adm> getAdmList(@Param("columnName") String name, @Param("value") String value);
}
