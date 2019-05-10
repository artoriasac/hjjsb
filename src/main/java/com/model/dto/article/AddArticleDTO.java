package com.model.dto.article;

import lombok.Data;

import java.util.List;

@Data
public class AddArticleDTO {

    private String title;

    private String content;

    private List<Integer> nodeIds;
}
