package com.iszuo.pojo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
public class User {
    @NotNull
    private Integer id;//主键ID
    private String username;//用户名
    @JsonIgnore // springmvc将当前对象转为json字符串时忽略password
    private String password;//密码
    @NotNull
    @Pattern(regexp = "^\\S{1,10}$")    // 正则限制字符长度
    private String nickname;//昵称
    @Email      // 邮箱格式
    @NotNull    // 非空
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
