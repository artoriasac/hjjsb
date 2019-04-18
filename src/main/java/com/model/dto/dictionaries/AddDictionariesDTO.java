package com.model.dto.dictionaries;

import lombok.Data;

@Data
public class AddDictionariesDTO {
    private String radicals;

    private Integer stroke;

    private String shape;

    private String word;
}
