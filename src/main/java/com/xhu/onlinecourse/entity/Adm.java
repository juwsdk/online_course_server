package com.xhu.onlinecourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adm {
    private int admId;
    private String admName;
    private String admPassword;
    private int admAuthoritylevels;
}
