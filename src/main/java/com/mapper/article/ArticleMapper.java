package com.mapper.article;

import com.model.entity.article.Article;
import com.model.vo.article.ArticleListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    List<ArticleListVO> selectArticleList(@Param("title") String title,@Param("content") String content,@Param("categoryId") Integer categoryId);
}