package com.iszuo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.iszuo.mapper.ArticleMapper;
import com.iszuo.pojo.Article;
import com.iszuo.pojo.PageBean;
import com.iszuo.service.ArticleService;
import com.iszuo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    // 添加文章
    @Override
    public void add(Article article){
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String, Object> map = ThreadLocalUtil.get();
        article.setCreateUser((Integer)map.get("id"));
        articleMapper.add(article);
    }

    // 条件列表查询
    @Override
    public PageBean<Article> list(Integer pageNum,Integer pageSize,Integer categoryId,String state){
        // 1、创建pagebean对象
        PageBean<Article> pageBean = new PageBean<>();


        // 2、开启分页查询  pageHelper
                    // 当前页，每页条数
        PageHelper.startPage(pageNum, pageSize);


        // 3、调用mapper
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer)map.get("id");
        List<Article> ls = articleMapper.list(userId,categoryId,state);
        Page<Article> p = (Page<Article>)ls;

        // 把数据填充到pageBean中
        pageBean.setTotal(p.getTotal());
        pageBean.setItems(p.getResult());
        return pageBean;
    }

    // 显示文章详情
    @Override
    public Article showDetail(Integer id){
        return articleMapper.showDetail(id);
    }

    // 更新文章
    @Override
    public void update(Article article){
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }

    @Override
    public void del(Integer id){
        articleMapper.del(id);
    }
}
