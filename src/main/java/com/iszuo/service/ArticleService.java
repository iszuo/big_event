package com.iszuo.service;

import com.iszuo.pojo.Article;
import com.iszuo.pojo.PageBean;
import com.iszuo.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface ArticleService{

    // 添加文章
    void add(Article article);

    // 条件列表查询
    PageBean<Article> list(Integer pageNum,Integer pageSize,Integer categoryId,String state);

    // 显示文章详情
    Article showDetail(Integer id);

    // 更行文章
    void update(Article article);

    // 删除
    void del(Integer id);
}
