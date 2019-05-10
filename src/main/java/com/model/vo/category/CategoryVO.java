package com.model.vo.category;

import com.model.vo.article.ArticleListVO;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVO {
    private String content;
    private List<ArticleListVO> list;
}
