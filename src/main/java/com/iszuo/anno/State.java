package com.iszuo.anno;

import com.iszuo.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotEmpty;

import java.lang.annotation.*;

@Documented     // 元注解
@Target({ElementType.FIELD})     // 元注解，表示可以使用的地方（方法、类、属性……）
@Retention(RetentionPolicy.RUNTIME)     // 元注解，保留时间段(编译时、源码时、运行时……）
@Constraint( validatedBy = StateValidation.class)      // 指提供校验规则的类
public @interface State{
    // 提供校验失败后的提示信息
    String message() default "state参数的值只能是已发布或草稿";
    // 指定分组
    Class<?>[] groups() default {};
    // 负载    获取到state注解的附加信息       一般用不到
    Class<? extends Payload>[] payload() default {};

}
