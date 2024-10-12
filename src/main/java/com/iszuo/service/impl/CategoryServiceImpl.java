package com.iszuo.service.impl;

import com.iszuo.mapper.CategoryMapper;
import com.iszuo.pojo.Category;
import com.iszuo.pojo.Result;
import com.iszuo.service.CategoryService;
import com.iszuo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryMapper categoryMapper;

    // 添加文章
    @Override
    public void add(Category category){
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String, Object> map = ThreadLocalUtil.get();
        category.setCreateUser((Integer)map.get("id"));
        categoryMapper.add(category);
    }

    // 获取所有文章
    @Override
    public List<Category> list(){
        Map<String, Object> map = ThreadLocalUtil.get();
        return categoryMapper.list((Integer)map.get("id"));
    }

    // 文章详情
    @Override
    public Category detail(Integer id){
        return categoryMapper.detail(id);
    }

    // 更新文章
    @Override
    public void update(Category category){
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }
}
