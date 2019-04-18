package com.model.vo.dictionaries;

import lombok.Data;

import java.util.List;

@Data
public class SelectDictionariesPronunciationVO {
    private Integer id;

    private String pronunciation;

    private Integer dictionariesId;

    private String pinyin;

    private String tone;

    private String createTime;

    private List<SelectDictionariesInfoVO> list;
}
