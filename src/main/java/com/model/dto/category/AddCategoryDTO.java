package com.model.dto.category;

import lombok.Data;

import java.util.List;

@Data
public class AddCategoryDTO {
    private String content;
    private List<Integer> articleIds;
}
