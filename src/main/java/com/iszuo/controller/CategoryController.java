package com.iszuo.controller;

import com.iszuo.pojo.Category;
import com.iszuo.pojo.Result;
import com.iszuo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController{

    @Autowired
    private CategoryService categoryService;

    // 添加文章
    @PostMapping
    public Result add(@RequestBody @Validated(Category.add.class) Category category){
        categoryService.add(category);
        return Result.success();
    }

    // 获取所有文章
    @GetMapping
    public Result<List<Category>> list(){
        List<Category> ls = categoryService.list();
        return Result.success(ls);
    }

    // 获取文章详情
    @GetMapping("/detail")
    public Result<Category> detail(Integer id){
        Category category = categoryService.detail(id);
        return Result.success(category);
    }

    // 更新文章
    @PutMapping
    public Result update(@RequestBody @Validated(Category.update.class) Category category){
        categoryService.update(category);
        return Result.success();
    }
}
