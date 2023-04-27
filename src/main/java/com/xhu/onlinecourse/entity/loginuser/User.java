package com.xhu.onlinecourse.entity.loginuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;
    private String password;
    private String loginType;
}