package com.xhu.onlinecourse.service;

import java.util.Map;

public interface LoginRegisterService {
     Map<Long,String> getUserInfo(Long userId,String userType);
     Integer register(Object obj,String userTpe);
     Integer getAdminType(Long adminId);
}
