package com.mapper.category;

import com.model.entity.category.CategoryArticle;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CategoryArticle record);

    int insertSelective(CategoryArticle record);

    CategoryArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CategoryArticle record);

    int updateByPrimaryKey(CategoryArticle record);
}