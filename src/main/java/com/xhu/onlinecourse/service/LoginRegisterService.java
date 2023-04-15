package com.xhu.onlinecourse.service;

import java.util.Map;

public interface LoginRegisterService {
     Map<Long,String> getUserInfo(Long userId,String userType);
}
