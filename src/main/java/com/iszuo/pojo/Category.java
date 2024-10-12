package com.iszuo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.awt.*;
import java.time.LocalDateTime;

@Data
public class Category {
    @NotNull(groups = update.class)    // 非空
    private Integer id;//主键ID
    @NotEmpty   // 非空，且不能为空字符串
    private String categoryName;//分类名称
    @NotEmpty   // 非空，且不能为空字符串
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")    // 设置日期格式
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间


    // 如果说某个校验项没有指定分组，默认属于Default分组
    // 分组之间可以继承  A extends B    那么A中拥有B中所有的校验项
    // categoryName与categoryAlias 没有指定分组——>Default，由于add和update同时继承Default，所以categoryName与categoryAlias同时属于add和update

    public interface add extends Default{}
    public interface update extends Default{}
}
