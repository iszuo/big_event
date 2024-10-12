package com.iszuo.service;

import com.iszuo.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService{

    // 根据用户名查询用户
    User findByUserName(String username);

    // 注册用户
    void register(String username,String password);

    // 更新
    void update(User user);

    // 更新头像
    void updateAvatar(String avatarUrl);

    // 修改密码
    void updatePwd(String newPwd);
}
