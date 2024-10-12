package com.iszuo.exception;

import com.iszuo.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice   // 全局异常处理器
public class GlobalExceptionHandler{


    @ExceptionHandler(Exception.class)  // 指定异常处理方法
    public Result handlerException(Exception e){
        e.printStackTrace();    // 异常信息输出到控制台
        return Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失败");
    }
}
