package com.zyz.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //用户名、昵称、密码
    private String username;
    private String nickname;
    private String password;
}
