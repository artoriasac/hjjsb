package com.model.entity.dictionaries;

import java.util.Date;

public class DictionariesInfo {
    private Integer id;

    private Integer pronunciationId;

    private String info;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPronunciationId() {
        return pronunciationId;
    }

    public void setPronunciationId(Integer pronunciationId) {
        this.pronunciationId = pronunciationId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}