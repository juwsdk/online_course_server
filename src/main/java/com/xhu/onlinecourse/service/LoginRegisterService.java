package com.xhu.onlinecourse.service;

import com.xhu.onlinecourse.entity.loginuser.User;

public interface LoginRegisterService {
     Boolean getUserInfo(User user);

     Boolean checkUserId(User user);

     Integer register(Object obj, String userTpe);

     Integer getAdminType(Long adminId);
}
