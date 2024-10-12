package com.iszuo.mapper;

import com.iszuo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper{
    // 根据用户名查询用户
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    // 添加
    @Insert("insert into user(username,password,create_time,update_time)" +
            " values(#{username},#{password},now(),now())")
    void add(String username,String password);

    // 更新
    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);

    // 上传头像
    @Update("update user set update_time=now(),user_pic=#{avatarUrl} where id=#{id}")
    void updateAvatarUrl(String avatarUrl,Integer id);

    // 更新密码
    @Update("update user set password=#{md5Str},update_time=now() where id=#{id}")
    void updatePwd(String md5Str, Integer id);
}
