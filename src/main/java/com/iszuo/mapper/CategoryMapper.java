package com.iszuo.mapper;


import com.iszuo.pojo.Category;
import com.iszuo.pojo.Result;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CategoryMapper{

    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time)" +
            " values(#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);

    @Select("select * from category where create_user=#{id}")
    List<Category> list(Integer id);

    @Select("select * from category where id=#{id}")
    Category detail(Integer id);

//    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")

    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=#{updateTime} where id=#{id}")
    void update(Category category);
}
