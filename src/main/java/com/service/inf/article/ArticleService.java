package com.service.inf.article;

import com.common.model.DataInfo;
import com.model.dto.article.AddArticleDTO;
import com.model.vo.article.ArticleListVO;
import com.model.vo.article.GetArticleVO;

public interface ArticleService {
    void addArticle(AddArticleDTO addArticleDTO);

    DataInfo<ArticleListVO> selectArticleList(String title, String content, Integer categoryId, Integer page, Integer pageSize);

    GetArticleVO selectArticleById(Integer id);
}
