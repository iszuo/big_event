package com.iszuo.controller;

import com.iszuo.pojo.Article;
import com.iszuo.pojo.PageBean;
import com.iszuo.pojo.Result;
import com.iszuo.service.ArticleService;
import com.iszuo.utils.JwtUtil;
import com.sun.net.httpserver.spi.HttpServerProvider;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

// 获取文章详情
// 更新文章
// 删除文章

@RestController
@RequestMapping("/article")
public class ArticleController{

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody Article article){
        articleService.add(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
        System.out.println(pageNum + "   " + pageSize + "   " + categoryId + "   " + state);
        PageBean<Article> pageBean = articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pageBean);
    }

    @GetMapping("/detail")
    public Result<Article> showDetail(@RequestParam Integer id){
        return Result.success(articleService.showDetail(id));
    }

    @PutMapping
    public Result update(@RequestBody Article article){
        articleService.update(article);
        return Result.success();
    }

    @DeleteMapping
    public Result del(@RequestParam Integer id){
        articleService.del(id);
        return Result.success();
    }
}














