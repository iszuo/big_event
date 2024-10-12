package com.iszuo.service.impl;

import com.iszuo.mapper.UserMapper;
import com.iszuo.pojo.User;
import com.iszuo.service.UserService;
import com.iszuo.utils.Md5Util;
import com.iszuo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findByUserName(String username){
        User u = userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username,String password){
        // 对密码加密
        String md5String =Md5Util.getMD5String(password);
        // 添加
        userMapper.add(username,md5String);
    }

    @Override
    public void update(User user){
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl){
//        Map<String, Object> map = ThreadLocalUtil.get();
//        Integer id = (Integer)map.get("id");
        userMapper.updateAvatarUrl(avatarUrl, getUserId());
    }

    @Override
    public void updatePwd(String newPwd){

        userMapper.updatePwd(Md5Util.getMD5String(newPwd),getUserId());
    }

    private Integer getUserId(){
        Map<String, Object> map = ThreadLocalUtil.get();
        return (Integer)map.get("id");
    }
}
