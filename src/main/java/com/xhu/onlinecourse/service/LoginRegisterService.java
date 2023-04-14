package com.xhu.onlinecourse.service;

import java.util.Map;

public interface LoginRegisterService {
    public Map<Long,String> getUserInfo(Long userId,String userType);
}
