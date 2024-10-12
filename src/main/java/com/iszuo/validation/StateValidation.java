package com.iszuo.validation;

import com.iszuo.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// 给哪个注解提供
public class StateValidation implements ConstraintValidator</*给哪个注解提供校验规则*/State, /*校验的数据类型*/String>{

    /**
     *
     * @param s  将来要校验的规则
     * @param constraintValidatorContext
     * @return  返回false校验不通过，   true校验通过
     */
    @Override
    public boolean isValid(String s,ConstraintValidatorContext constraintValidatorContext){
        // 提供校验规则
        if(s.equals("已发布") || s.equals("草稿")){
            return true;
        }
        return false;
    }
}
