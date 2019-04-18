package com.model.vo.dictionaries;

import lombok.Data;

@Data
public class SelectDictionariesVO {
    private Integer id;

    private String radicals;

    private Integer stroke;

    private String shape;

    private String word;

    private String createTime;
}
