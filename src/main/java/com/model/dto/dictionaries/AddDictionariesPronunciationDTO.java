package com.model.dto.dictionaries;

import lombok.Data;

@Data
public class AddDictionariesPronunciationDTO {
    private String pronunciation;

    private Integer dictionariesId;

    private String pinyin;

    private String tone;
}
