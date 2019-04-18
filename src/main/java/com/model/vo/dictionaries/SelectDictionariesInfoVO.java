package com.model.vo.dictionaries;

import lombok.Data;

@Data
public class SelectDictionariesInfoVO {
    private Integer id;

    private Integer pronunciationId;

    private String info;

    private String createTime;
}
