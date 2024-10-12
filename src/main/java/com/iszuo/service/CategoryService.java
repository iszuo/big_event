package com.iszuo.service;


import com.iszuo.pojo.Category;
import com.iszuo.pojo.Result;

import java.util.List;

public interface CategoryService{
    // 添加文章
    void add(Category category);

    // 输出文章列表
    List<Category> list();

    // 文章详情
    Category detail(Integer id);

    // 更新文章
    void update(Category category);
}
