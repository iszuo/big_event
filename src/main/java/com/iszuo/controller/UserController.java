package com.iszuo.controller;

import com.iszuo.pojo.Result;
import com.iszuo.pojo.User;
import com.iszuo.service.UserService;
import com.iszuo.utils.JwtUtil;
import com.iszuo.utils.Md5Util;
import com.iszuo.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Validated  // 参数校验
@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;


    @PostMapping("/regist")
    // @Pattern(regexp = "^\\S{5,16}$")  设置参数长度应为5-16位，且不能为空
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        // 查询当前用户名是否已被占用
        User u = userService.findByUserName(username);
        if(u == null){
            userService.register(username,password);
            return Result.success();
        }else {
            return Result.error("用户名已被占用");
        }
    }


    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp="^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){

        // 查询用户是否存在
        User u = userService.findByUserName(username);
        if(u == null){
            return Result.error("当前用户不存在。");
        }
        if(Md5Util.getMD5String(password).equals(u.getPassword())){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",u.getId());
            claims.put("username",u.getUsername());
            String token = JwtUtil.getToken(claims);
            return Result.success(token);
        }else{
            return Result.error("登录密码错误。");
        }
    }


    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token){
        // 根据用户名查询用户
      /*  Map<String,Object> claims = JwtUtil.parseToken(token);
        String username = (String)claims.get("username");*/
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String)map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }


    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){

        userService.update(user);
        return Result.success();
    }


    @PatchMapping("/updateAvatar")
    public Result updateAvatar(/*从请求中提取参数并将其绑定到方法的参数上*/@RequestParam /*只能时url*/@URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params){
        // 参数校验
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        // 验证信息完整
        if(oldPwd.trim().length() == 0 || newPwd.trim().length() == 0 || rePwd.trim().length() == 0){
            return Result.error("信息填写不完整");
        }

        // 是否与原密码相同
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String)map.get("username");
        User loginUser = userService.findByUserName(username);
        if(!Md5Util.getMD5String(oldPwd).equals(loginUser.getPassword())){
            return Result.error("原密码填写错误");
        }
        // 验证两次密码是否相同
        if(!newPwd.trim().equals(rePwd)){
            return Result.error("输入的两次密码不同");
        }

        userService.updatePwd(newPwd);
        return Result.success();


    }



//    @PostMapping
//    // @Pattern(regexp = "^\\S{5,16}$")  设置参数长度应为5-16位，且不能为空
//    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
//        if(username != null && username.length() >= 5 && username.length() <= 10 &&
//                password != null && password.length() >= 5 && password.length() <= 10
//        ){
//            // 查询当前用户名是否已被占用
//            User u = userService.findByUserName(username);
//            if(u == null){
//                userService.register(username,password);
//                return Result.success();
//            }else {
//                return Result.error("用户名已被占用");
//            }
//        }else {
//            return Result.error("输入的账号或密码长度应在5-10位");
//        }
//
//    }

}
