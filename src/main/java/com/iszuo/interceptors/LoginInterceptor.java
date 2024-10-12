package com.iszuo.interceptors;

import com.iszuo.utils.JwtUtil;
import com.iszuo.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor{

    // 前置拦截器，在控制器方法执行前执行
    @Override
    public boolean preHandle(HttpServletRequest request/* 请求头  */,HttpServletResponse response,Object handler) throws Exception{
        // 令牌验证
        String token = request.getHeader("Authorization");
        try{
            // 解析token中的载荷——>map
            Map<String, Object> claims = JwtUtil.parseToken(token);
            // 将业务数据存储到threadlocal中
            ThreadLocalUtil.set(claims);
            // 放行
            return true;
        }catch(Exception e){
            response.setStatus(401);
            // 拦截
            return false;
        }
    }

    // 后置拦截器，请求处理后执行的一个方法，通常用于清理资源或执行一些后处理逻辑
    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception{
        // 清空ThreadLocal中的数据，防止内容泄漏
        ThreadLocalUtil.remove();
    }
}
